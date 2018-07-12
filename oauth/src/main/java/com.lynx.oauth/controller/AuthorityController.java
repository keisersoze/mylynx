package com.lynx.oauth.controller;

import com.lynx.oauth.model.Authority;
import com.lynx.oauth.service.MyAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorityController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("myAuthorityService")
    @Autowired
    private MyAuthorityService authorityService;


    @PutMapping("/authority/{name}")
    public void putClient(@PathVariable(value="name") String username){
        authorityService.put(new Authority(username));
    }

    @GetMapping("/authority/{name}")
    public GrantedAuthority getClient(@PathVariable(value="name") String name){
        return authorityService.findByName(name);
    }

    @DeleteMapping("/authority/{name}")
    public void deleteClient(@PathVariable(value="name") String name){
        authorityService.deleteByUsername(name);
    }

    @GetMapping("/authority")
    public List<GrantedAuthority> findAllClients(){
        return authorityService.findAll();
    }
}
