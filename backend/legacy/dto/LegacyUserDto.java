package backend.legacy.dto;

import java.util.Optional;

@Deprecated(forRemoval = true)
public record LegacyUserDto(Optional<String> displayName) {}
