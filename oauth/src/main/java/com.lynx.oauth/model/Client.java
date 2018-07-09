package com.lynx.oauth.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
public class Client implements ClientDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private String clientId;

    @Column(unique = true)
    @NotNull
    private String clientSecret;

    private String authorities;

    @NotNull
    private int accessTokenValiditySeconds;

    @NotNull
    private int refreshTokenValiditySeconds;

    @ElementCollection
    private Set<String> resourceIds;

    @ElementCollection
    private Set<String> authorizedGrantTypes;

    private boolean isSecretRequired, isScoped, isAutoApprove;

    public Client() {
        this.isSecretRequired = true;
        this.isScoped = false;
        this.isAutoApprove = false;
        authorizedGrantTypes = new HashSet<>(Arrays.asList("client_credentials"));
        resourceIds = new HashSet<>(Arrays.asList("resource_1"));
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getScope() {
        return null;
    }
}
