/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.money.security;

import com.pamarin.money.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author anonymous
 */
public class SecurityUtils {

    public static User getUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = null;

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                user = (User) principal;
            } else {
                user = new User("anonymous", null);
            }
        }

        return user;
    }

    public static boolean isAnonymous() {
        return "anonymous".equals(getUserLogin().getUsername());
    }
}
