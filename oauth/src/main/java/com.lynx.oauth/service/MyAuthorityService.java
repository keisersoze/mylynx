package com.lynx.oauth.service;

import com.lynx.oauth.DAO.AuthorityRepository;
import com.lynx.oauth.exceptions.ResourceNotFoundException;
import com.lynx.oauth.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MyAuthorityService  {
    @Autowired
    private AuthorityRepository authorityRepository;


    public GrantedAuthority findByName(String name) throws UsernameNotFoundException {
        GrantedAuthority authority = authorityRepository.findByName(name);
        if (authority == null) {
            throw new UsernameNotFoundException(name);
        }
        return authority;
    }

    public List<GrantedAuthority> findAll() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorityRepository.findAll().forEach(authority -> authorities.add(authority));
        return authorities;
    }

    public void put(GrantedAuthority authority){
        Authority user = new Authority(authority);
        authorityRepository.save(user);
    }

    public void deleteByUsername(String username) throws ResourceNotFoundException {
        if (authorityRepository.existsByName(username)) {
            authorityRepository.deleteByName(username);
        }else {
            throw new ResourceNotFoundException("user",username);
        }
    }

}
