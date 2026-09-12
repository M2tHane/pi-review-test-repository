package backend.walkthrough.orders;

import java.util.ArrayList;
import java.util.List;

public final class OrderExport {
    public List<String> sortedForExport(OrderCatalog catalog) {
        List<String> rows = new ArrayList<>(catalog.listOrders());
        rows.sort(String::compareTo);
        return List.copyOf(rows);
    }
}
