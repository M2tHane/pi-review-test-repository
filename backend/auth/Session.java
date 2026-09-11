package backend.auth;

import java.util.Set;

/** Created by the authentication filter after validating the signed session. */
public record Session(String userId, String tenantId, Set<String> roles) {}
