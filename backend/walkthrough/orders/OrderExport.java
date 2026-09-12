package backend.walkthrough.orders;

import java.util.List;

public final class OrderExport {
    public List<String> sortedForExport(OrderCatalog catalog) {
        List<String> rows = catalog.listOrders();
        rows.sort(String::compareTo);
        return rows;
    }
}
