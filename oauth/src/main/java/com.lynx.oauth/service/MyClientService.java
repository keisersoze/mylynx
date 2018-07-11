package com.lynx.oauth.service;

import com.lynx.oauth.DAO.ClientRepository;
import com.lynx.oauth.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service
public class MyClientService implements ClientDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ClientDetails client = clientRepository.findByClientId(clientId);
        if (client == null) {
            throw new UsernameNotFoundException(clientId);
        }
        BaseClientDetails details = new BaseClientDetails(client);
        return details;
    }


    public List<ClientDetails> findAll() throws ClientRegistrationException{
        List<ClientDetails> clients = new ArrayList<>();
        clientRepository.findAll().forEach(client -> clients.add(client));
        return clients;
    }

    public void put(ClientDetails clientDetails) throws Exception{
        Client client = new Client(clientDetails);
        clientRepository.save(client);
    }





}
