package backend.order.dto;

import java.util.Optional;

// M1: rerun review after the confirmed rule was versioned.
public record OrderDto(String id, Optional<String> note) {}

// M2 inline finding verification.
