package backend.api;

import backend.auth.Session;
import backend.reporting.CsvReport;
import backend.storage.LedgerStore;

public final class ReportController {
    private final LedgerStore store;

    public ReportController(LedgerStore store) {
        this.store = store;
    }

    /** GET /tenants/{tenant}/report.csv */
    public String export(Session session, String tenant) {
        if (!session.roles().contains("auditor")) {
            throw new SecurityException("auditor role required");
        }
        return CsvReport.encode(store.rows(tenant));
    }
}
