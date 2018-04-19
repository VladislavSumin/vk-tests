package ru.falseteam.vktests.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Contains all user roles
 *
 * @author Sumin Vladislav
 * @version 1.0
 */
public enum Role implements GrantedAuthority {
    USER,
    TEACHER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
