package com.lynx.oauth.controller;

import com.lynx.oauth.DAO.ClientRepository;
import com.lynx.oauth.model.Client;
import com.lynx.oauth.service.MyClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("myClientDetailsService")
    @Autowired
    private MyClientDetailsService myClientDetailsService;

    @Qualifier("myUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/prova3")
    public void prova(){
        Client client = new Client("openlaws",passwordEncoder.encode("secret"));
        clientRepository.save(client);
    }

    @GetMapping("/client/{client_id}")
    public ClientDetails getClient(@PathVariable(value="client_id") String clientId){
        return myClientDetailsService.loadClientByClientId(clientId);
    }


    @GetMapping("/client")
    public List<ClientDetails> findAllClients(){
        return myClientDetailsService.findAll();
    }

}
