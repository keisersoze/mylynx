package com.lynx.oauth.controller;


import com.lynx.oauth.DAO.UserRepository;
import com.lynx.oauth.model.User;
import com.lynx.oauth.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("myUserService")
    @Autowired
    private MyUserService userService;

    @PutMapping("/user/prova")
    public void putClient() throws Exception {
        userService.put(new User("ciccio", "pasticcio"));
    }

    @PutMapping("/user/{username}")
    public void putClient(@PathVariable(value="username") String username, @RequestBody User userDetails){
        String encPassword = passwordEncoder.encode(userDetails.getPassword());
        userDetails.setPassword(encPassword);
        userDetails.setUsername(username);
        try {
            userService.put(userDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/user/{username}")
    public UserDetails getClient(@PathVariable(value="username") String username){
        return userService.loadUserByUsername(username);
    }


    @GetMapping("/user")
    public List<UserDetails> findAllClients(){
        return userService.findAll();
    }
}
