package ru.falseteam.vktests.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Contains all user roles
 *
 * @author Sumin Vladislav
 * @version 1.1
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_TEACHER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
