package com.lynx.oauth.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
public class Client implements ClientDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String clientId;

    @NotNull
    private String clientSecret;

    @NotNull
    private int accessTokenValiditySeconds;

    @NotNull
    private int refreshTokenValiditySeconds;

    @ElementCollection
    private Set<GrantedAuthority> authorities;

    @ElementCollection
    private Set<String> resourceIds;

    @ElementCollection
    private Set<String> authorizedGrantTypes;

    @ElementCollection
    private Set<String> redirectUri;

    @ElementCollection
    private Set<String> scope;

    private boolean isSecretRequired, isScoped, isAutoApprove;


    public Client() {
    }

    public Client(String clientId, String clientSecret) {
        this.clientId= clientId;

        this.clientSecret= clientSecret;

        redirectUri= new HashSet<>();
        redirectUri.add("http://anywhere.com");

        authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));

        authorizedGrantTypes = new HashSet<>(Arrays.asList("client_credentials"));

        resourceIds = new HashSet<>(Arrays.asList("oauth2_id"));

        scope = new HashSet<>(Arrays.asList("read"));
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
        return false;
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
        return authorities;
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
        return redirectUri;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return scope;
    }
}
