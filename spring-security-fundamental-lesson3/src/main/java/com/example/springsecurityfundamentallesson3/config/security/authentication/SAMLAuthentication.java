package com.example.springsecurityfundamentallesson3.config.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import java.util.Collection;

public class SAMLAuthentication extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal;
    private Object credentials;
    private boolean isSAMLResponse;

    public SAMLAuthentication(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.setAuthenticated(false);
    }

    public SAMLAuthentication(Object principal, Object credentials, boolean isSAMLRespone, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.isSAMLResponse = isSAMLRespone;
        this.setAuthenticated(false);
    }

    public SAMLAuthentication(Object principal, Object credentials,
                              Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.setAuthenticated(true);
    }

    public boolean isSAMLResponse() {
        return isSAMLResponse;
    }

    public void setSAMLResponse(boolean SAMLResponse) {
        isSAMLResponse = SAMLResponse;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}