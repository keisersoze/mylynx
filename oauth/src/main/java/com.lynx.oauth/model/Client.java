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
    private List<GrantedAuthority> authorities;

    @ElementCollection
    private Set<String> resourceIds;

    @ElementCollection
    private Set<String> authorizedGrantTypes;

    @ElementCollection
    private Set<String> registeredRedirectUri;

    @ElementCollection
    private Set<String> scope;

    private boolean isSecretRequired, isScoped, isAutoApprove;


    public Client() {
    }

    public Client(ClientDetails prototype) {
        this();
        this.setAccessTokenValiditySeconds(prototype.getAccessTokenValiditySeconds());
        this.setRefreshTokenValiditySeconds(prototype.getRefreshTokenValiditySeconds());
        this.setAuthorities(prototype.getAuthorities());
        this.setAuthorizedGrantTypes(prototype.getAuthorizedGrantTypes());
        this.setClientId(prototype.getClientId());
        this.setClientSecret(prototype.getClientSecret());
        this.setRegisteredRedirectUri(prototype.getRegisteredRedirectUri());
        this.setScope(prototype.getScope());
        this.setResourceIds(prototype.getResourceIds());
    }
    public Client(String clientId, String clientSecret) {
        this.clientId= clientId;

        this.clientSecret= clientSecret;

        registeredRedirectUri = new HashSet<>();
        registeredRedirectUri.add("http://anywhere.com");

        authorities = new ArrayList<>();
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
        return registeredRedirectUri;
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

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = new ArrayList<>(authorities);
    }

    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public void setRegisteredRedirectUri(Set<String> registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }
}
