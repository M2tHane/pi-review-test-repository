# Legacy response compatibility

The public v1 response currently exposes a displayName object with a present flag.
Its Java wire DTO uses Optional; the current ACTIVE DTO rule prohibits Optional fields.
The v1 route is isolated at /api/v1/legacy-user and returns 410 from 2026-10-01 UTC.
The v2 route returns a nullable string and is the migration target.
This is an isolated review fixture; no service from this branch is deployed.
