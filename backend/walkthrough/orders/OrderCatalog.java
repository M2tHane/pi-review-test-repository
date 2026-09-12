package backend.walkthrough.orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class OrderCatalog {
    private final List<String> orders = new ArrayList<>();

    public void addOrder(String orderId) {
        orders.add(Objects.requireNonNull(orderId));
    }

    public List<String> listOrders() {
        return orders;
    }
}
