/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import org.eclipse.persistence.annotations.Index;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author jittagornp
 */
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @TableGenerator(
            name = "user_generator",
            table = "income_sequence",
            pkColumnName = "name",
            valueColumnName = "value",
            pkColumnValue = "user"
    )
    @GeneratedValue(
            generator = "user_generator",
            strategy = GenerationType.TABLE
    )
    @Column(name = "user_id")
    private Integer id;
    @Index
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean enabled = Boolean.TRUE;
    @Column(name = "password_expired", nullable = false)
    private Boolean passwordExpired = Boolean.FALSE;
    @Column(name = "account_expired", nullable = false)
    private Boolean accountExpired = Boolean.FALSE;
    @Column(name = "account_locked", nullable = false)
    private Boolean accountLocked = Boolean.FALSE;
    @Lob
    @Column(name = "activate_code")
    private String activateCode;
    //
    @OneToOne(mappedBy = "owner")
    private Settings settings;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !passwordExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public String getActivateCode() {
        return activateCode;
    }

    public void setActivateCode(String activateCode) {
        this.activateCode = activateCode;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
