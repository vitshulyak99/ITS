package com.bsuir.its.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {
    private final String id;
    private final String password;

    /***
     * email is username for UserDetails
     */
    private final String email;
    private final boolean enabled;
    private final Date lastPasswordResetDate;
    private final  Collection<? extends GrantedAuthority> authorities;

    public JwtUser(String id,
                   String password,
                   String email,
                   boolean enable,
                   Date lastPasswordResetDate,
                   Collection<? extends GrantedAuthority> authorities) {
        this.id = id;

        this.password = password;
        this.email = email;
        this.enabled = enable;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.authorities = authorities;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }


}
