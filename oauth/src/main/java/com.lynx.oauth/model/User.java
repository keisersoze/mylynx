package com.lynx.oauth.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.client.Jackson2ArrayOrStringDeserializer;
import org.springframework.security.oauth2.provider.client.JacksonArrayOrStringDeserializer;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class User implements UserDetails{

    @Id
    @NotNull
    @JsonProperty("username")
    @com.fasterxml.jackson.annotation.JsonProperty("username")
    private String username;

    @NotNull
    @JsonProperty("password")
    @com.fasterxml.jackson.annotation.JsonProperty("password")
    private String password;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "authority")
    )
    private List <Authority> authorities;

    @JsonProperty("account_non_expired")
    @com.fasterxml.jackson.annotation.JsonProperty("account_non_expired")
    private boolean accountNonExpired;

    @JsonProperty("account_non_locked")
    @com.fasterxml.jackson.annotation.JsonProperty("account_non_locked")
    private boolean accountNonLocked;

    @JsonProperty("credentials_non_expired")
    @com.fasterxml.jackson.annotation.JsonProperty("credentials_non_expired")
    private boolean credentialsNonExpired;

    @JsonProperty("enabled")
    @com.fasterxml.jackson.annotation.JsonProperty("enabled")
    private boolean enabled;

    public User() {
        authorities = new ArrayList<>();
    }

    public User(UserDetails userDetails){
        this();
        this.setUsername(userDetails.getUsername());
        this.setPassword(userDetails.getPassword());
        this.setAuthorities(userDetails.getAuthorities());
        this.setAccountNonExpired(userDetails.isAccountNonExpired());
        this.setAccountNonLocked(userDetails.isAccountNonLocked());
        this.setCredentialsNonExpired(userDetails.isCredentialsNonExpired());
        this.setEnabled(userDetails.isEnabled());
    }

    public User(String username, String password, String authorities) {
        this.username = username;
        this.password = password;
        if (StringUtils.hasText(authorities)) {
            AuthorityUtils
                    .commaSeparatedStringToAuthorityList(authorities)
                    .forEach(grantedAuthority -> this.authorities.add(new Authority(grantedAuthority)));
        }
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    @Override
    @JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    @JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    @JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    @JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    @JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    @JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        authorities
                .forEach(grantedAuthority -> this.authorities.add(new Authority(grantedAuthority)));
    }

    @JsonProperty("authorities")
    @JsonDeserialize(
            using = JacksonArrayOrStringDeserializer.class
    )
    @com.fasterxml.jackson.annotation.JsonProperty("authorities")
    @com.fasterxml.jackson.databind.annotation.JsonDeserialize(
            using = Jackson2ArrayOrStringDeserializer.class
    )
    private void setAuthoritiesAsStrings(Set<String> values) {
        AuthorityUtils
                .createAuthorityList((String[])values.toArray(new String[values.size()]))
                .forEach(grantedAuthority -> this.authorities.add(new Authority(grantedAuthority)));
    }


    @JsonProperty("authorities")
    @com.fasterxml.jackson.annotation.JsonProperty("authorities")
    private List<String> getAuthoritiesAsStrings() {
        return new ArrayList(AuthorityUtils.authorityListToSet(this.authorities));
    }

    @JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
