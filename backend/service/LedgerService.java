package backend.service;

import backend.storage.LedgerStore;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public final class LedgerService {
    private final LedgerStore store;
    private final ConcurrentHashMap<String, Receipt> completed = new ConcurrentHashMap<>();

    public LedgerService(LedgerStore store) {
        this.store = store;
    }

    public record Receipt(String tenant, String requestId, String from, String to, BigDecimal amount) {}

    public Receipt transfer(String tenant, String requestId, String from, String to, BigDecimal amount) {
        Receipt prior = completed.get(requestId);
        if (prior != null) {
            return prior;
        }
        if (amount == null || amount.signum() <= 0 || from.equals(to)) {
            throw new IllegalArgumentException("invalid transfer");
        }
        if (store.balance(tenant, from).compareTo(amount) < 0) {
            throw new IllegalArgumentException("insufficient funds");
        }
        store.debit(tenant, from, amount);
        store.credit(tenant, to, amount);
        Receipt receipt = new Receipt(tenant, requestId, from, to, amount);
        completed.put(requestId, receipt);
        return receipt;
    }
}
