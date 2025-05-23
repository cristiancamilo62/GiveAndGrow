package com.giveandgrow.infrastructure.security.service;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.giveandgrow.domain.model.organization.OrganizationDomain;
import com.giveandgrow.domain.model.security.Role;
import com.giveandgrow.domain.model.user.UserDomain;

import lombok.Getter;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    
    private final String email;
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;
    
    @Getter
    private final UUID id;
    
    @Getter
    private final Role role;

    @Getter
    private final Map<String, Object> responseAuth;

    public CustomUserDetails(
            String email, 
            String password, 
            boolean accountNonExpired, 
            boolean accountNonLocked,
            boolean credentialsNonExpired, 
            boolean enabled,
            Collection<? extends GrantedAuthority> authorities, 
            UUID id, 
            Role role,
            Map<String,Object> responseAuth) {
        this.email = email;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.authorities = authorities;
        this.id = id;
        this.role = role;
        this.responseAuth = responseAuth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}