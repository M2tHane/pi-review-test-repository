package backend.order.dto;

import java.util.Optional;

public record OrderDto(String id, Optional<String> note) {}
