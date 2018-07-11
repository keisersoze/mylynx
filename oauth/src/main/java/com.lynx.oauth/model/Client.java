package com.lynx.oauth.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@Entity
public class Client extends BaseClientDetails {

    @Id
    @Override
    public String getClientId() {
        return super.getClientId();
    }

    @NotNull
    @Override
    public String getClientSecret() {
        return super.getClientSecret();
    }

    @ElementCollection
    @Override
    public Set<String> getScope() {
        return super.getScope();
    }

    @ElementCollection
    @Override
    public Set<String> getResourceIds() {
        return super.getResourceIds();
    }

    @ElementCollection
    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return super.getAuthorizedGrantTypes();
    }

    @ElementCollection
    @Override
    public Set<String> getRegisteredRedirectUri() {
        return super.getRegisteredRedirectUri();
    }

    @ElementCollection
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }


    @Override
    public Integer getAccessTokenValiditySeconds() {
        return super.getAccessTokenValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return super.getRefreshTokenValiditySeconds();
    }
}
