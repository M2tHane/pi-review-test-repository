package backend.api;

import backend.auth.Session;
import backend.service.LedgerService;
import java.math.BigDecimal;
import java.util.Map;

public final class TransferController {
    private final LedgerService ledger;

    public TransferController(LedgerService ledger) {
        this.ledger = ledger;
    }

    public record Request(String requestId, String from, String to, BigDecimal amount) {}

    /** POST /payments/transfers; headers and request are supplied by the HTTP caller. */
    public LedgerService.Receipt transfer(Session session, Map<String, String> headers, Request request) {
        if (!session.roles().contains("finance")) {
            throw new SecurityException("finance role required");
        }
        String tenant = headers.getOrDefault("X-Tenant-Id", session.tenantId());
        return ledger.transfer(tenant, request.requestId(), request.from(), request.to(), request.amount());
    }
}
