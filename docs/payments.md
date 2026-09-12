# Payment and reporting contract

This branch is an isolated review fixture. It is not deployed and must not be merged into the default branch.

- A signed Session binds each finance or auditor user to one tenant. Roles grant access within that tenant.
- Transfers must be idempotent within a tenant, even when the same request arrives concurrently.
- A failed transfer must leave both balances unchanged. Concurrent transfers must not overdraw an account.
- Accounts must already exist before a transfer. Account labels are user supplied.
- Tenant auditors download the report and open it in spreadsheet software.
- Request handlers share one LedgerService and one LedgerStore.
- Only the service layer should make authorization-sensitive decisions about data access.
