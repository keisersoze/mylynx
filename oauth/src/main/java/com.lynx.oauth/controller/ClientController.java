package com.lynx.oauth.controller;

import com.lynx.oauth.model.Client;
import com.lynx.oauth.service.MyClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ClientController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("myClientService")
    @Autowired
    private MyClientService myClientService;


    @PutMapping("/client/{client_id}")
    public void putClient(@PathVariable(value="client_id") String clientId, @RequestBody Client clientDetails){
        String encPassword = passwordEncoder.encode(clientDetails.getClientSecret());
        clientDetails.setClientSecret(encPassword);
        clientDetails.setClientId(clientId);
        myClientService.put(clientDetails);
    }

    @GetMapping("/client/{client_id}")
    public ClientDetails getClient(@PathVariable(value="client_id") String clientId){
        return myClientService.loadClientByClientId(clientId);
    }


    @GetMapping("/client")
    public List<ClientDetails> findAllClients(){
        return myClientService.findAll();
    }

}
