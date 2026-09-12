package backend.walkthrough.orders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ArchivedOrderCatalog {
    private final List<String> archived = new ArrayList<>();

    public void archive(String orderId) {
        archived.add(Objects.requireNonNull(orderId));
    }

    public List<String> listArchivedOrders() {
        return Collections.unmodifiableList(archived);
    }
}
