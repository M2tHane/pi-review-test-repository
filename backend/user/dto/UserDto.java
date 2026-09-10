package backend.user.dto;

import java.util.Optional;

public record UserDto(String id, Optional<String> displayName) {}
