package com.lynx.oauth.controller;

import com.lynx.oauth.DAO.ClientRepository;
import com.lynx.oauth.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("myClientDetailsService")
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Qualifier("myUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/prova3")
    public void prova(){
        Client client = new Client("openlaws",passwordEncoder.encode("secret"));
        clientRepository.save(client);
    }

    @GetMapping("/prova4")
    public void prova2(){
        ClientDetails client = clientDetailsService.loadClientByClientId("openlaws");
        System.out.println(client.getClientSecret());
    }


}
