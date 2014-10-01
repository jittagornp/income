/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.security;

import com.pamarin.income.model.Account;
import com.pamarin.income.model.User;
import com.pamarin.income.service.UserService;
import com.pamarin.income.util.SpringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author jittagornp
 */
public class SecurityUtils {

    private static final String ANONYMOUS = "anonymous";

    public static Account getAccount() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Account account = null;

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof Account) {
                account = (Account) principal;
            } else {
                account = new Account(ANONYMOUS, null);
            }
        }

        return account;
    }

    public static User getUser() {
        Account account = getAccount();
        if (isAnonymous(account)) {
            return null;
        }

        return SpringUtils
                .getBean(UserService.class)
                .findByUserId(account.getId());
    }

    public static boolean isAnonymous() {
        return isAnonymous(getAccount());
    }

    public static boolean isAnonymous(Account account) {
        return ANONYMOUS.equals(account.getUsername());
    }
}
