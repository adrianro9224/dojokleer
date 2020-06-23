package com.kleer.dojo.domain;

import java.util.UUID;

public class AuthenticatedUser {
    private UUID userId;

    public AuthenticatedUser(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

}
