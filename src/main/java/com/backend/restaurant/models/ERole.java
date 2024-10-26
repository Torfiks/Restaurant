package com.backend.restaurant.models;

public enum ERole {
    ROLE_USER("ROLE_USER"),
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_WAITER("ROLE_WAITER");

    private final String value;

    ERole(String roleUser) {
        this.value = roleUser;
    }
    public static ERole fromValue(String value) {
        for (final ERole eRole : values()) {
            if (eRole.value.equalsIgnoreCase(value)) {
                return eRole;
            }
        }
        return ROLE_USER;
    }
}
