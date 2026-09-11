package backend.storage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** One shared instance backs all request handlers. Account balances are persistent state in this demo. */
public final class LedgerStore {
    private record Account(String tenant, String id, String label, BigDecimal balance) {}
    private final Map<String, Account> accounts = new HashMap<>();

    public synchronized void addAccount(String tenant, String id, String label, BigDecimal balance) {
        String key = tenant + ":" + id;
        if (accounts.containsKey(key)) {
            throw new IllegalArgumentException("account already exists");
        }
        accounts.put(key, new Account(tenant, id, label, balance));
    }

    private Account account(String tenant, String id) {
        Account account = accounts.get(tenant + ":" + id);
        if (account == null) {
            throw new IllegalArgumentException("account not found");
        }
        return account;
    }

    public synchronized BigDecimal balance(String tenant, String id) {
        return account(tenant, id).balance();
    }

    public synchronized void debit(String tenant, String id, BigDecimal amount) {
        Account account = account(tenant, id);
        accounts.put(tenant + ":" + id, new Account(tenant, id, account.label(), account.balance().subtract(amount)));
    }

    public synchronized void credit(String tenant, String id, BigDecimal amount) {
        Account account = account(tenant, id);
        accounts.put(tenant + ":" + id, new Account(tenant, id, account.label(), account.balance().add(amount)));
    }

    public synchronized List<List<String>> rows(String tenant) {
        List<List<String>> result = new ArrayList<>();
        for (Account account : accounts.values()) {
            if (account.tenant().equals(tenant)) {
                result.add(List.of(account.id(), account.label(), account.balance().toPlainString()));
            }
        }
        return result;
    }
}
