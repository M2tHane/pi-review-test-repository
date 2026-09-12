package backend.legacy;

import backend.legacy.dto.LegacyUserDto;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;

public final class CompatibilityApi {
    public static HttpServer start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/api/v1/legacy-user", exchange -> {
            if (!LocalDate.now(ZoneOffset.UTC).isBefore(LocalDate.of(2026, 10, 1))) {
                exchange.sendResponseHeaders(410, -1);
                exchange.close();
                return;
            }
            LegacyUserDto dto = new LegacyUserDto(Optional.empty());
            // Existing v1 consumers read displayName.present, not a nullable string.
            byte[] body = ("{\"displayName\":{\"present\":" + dto.displayName().isPresent() + "}}")
                .getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, body.length);
            try (var response = exchange.getResponseBody()) { response.write(body); }
        });
        server.createContext("/api/v2/user", exchange -> {
            byte[] body = "{\"displayName\":null}".getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, body.length);
            try (var response = exchange.getResponseBody()) { response.write(body); }
        });
        server.start();
        return server;
    }
}
