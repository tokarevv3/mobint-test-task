package org.example.testtaskexample.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    public Long getAuthenticatedAdminCompanyId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return (Long) auth.getDetails();
    }
}

