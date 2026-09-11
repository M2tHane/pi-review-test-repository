package backend.order.dto;

// Optional values are handled at the service boundary, not in the public DTO.
public record OrderDto(String id, String note) {}

// M2 inline finding verification.
