package com.lynx.oauth.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

        @GetMapping("/user")
        public ResponseEntity<?> userInfo(Principal principal){
            return  ResponseEntity.ok(principal);
        }

}
