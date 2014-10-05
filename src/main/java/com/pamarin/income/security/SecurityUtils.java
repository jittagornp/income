/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.security;

import com.pamarin.income.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author jittagornp
 */
public class SecurityUtils {

    private static final String ANONYMOUS = "anonymous";

    public static User getUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = null;

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                user = (User) principal;
            } else {
                user = new User(ANONYMOUS, null);
            }
        }

        return user;
    }

    public static boolean isAnonymous() {
        return isAnonymous(getUser());
    }

    public static boolean isAnonymous(User account) {
        return ANONYMOUS.equals(account.getUsername());
    }
}
