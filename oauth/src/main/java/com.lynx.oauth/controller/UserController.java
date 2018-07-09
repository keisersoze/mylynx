package com.lynx.oauth.controller;


import com.lynx.oauth.DAO.UserRepository;
import com.lynx.oauth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
        @Autowired
        private UserRepository userRepository;

        @GetMapping("/user")
        public ResponseEntity<?> userInfo(Principal principal){
            return  ResponseEntity.ok(principal);
        }

        @GetMapping("/prova")
        public void prova(){
            userRepository.save(new User("ciccio","pasticcio"));
        }

        @GetMapping("/prova2")
        public void prova2(){
            System.out.println(userRepository.findByUsername("ciccio").getPassword());
        }
}
