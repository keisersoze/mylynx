package com.lynx.oauth.service;

import com.lynx.oauth.DAO.ClientRepository;
import com.lynx.oauth.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class MyClientService implements ClientDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws EntityNotFoundException {
        ClientDetails client = clientRepository.findByClientId(clientId);
        if (client == null) {
            throw new EntityNotFoundException();
        }
        return client;
    }


    public List<ClientDetails> findAll(){
        List<ClientDetails> clients = new ArrayList<>();
        clientRepository.findAll().forEach(client -> clients.add(client));
        return clients;
    }

    public void put(ClientDetails clientDetails){
        Client client = new Client(clientDetails);
        clientRepository.save(client);
    }

    public void deleteByClientID(String clientId) throws  EntityNotFoundException{
        if (clientRepository.existsByClientId(clientId)) {
            clientRepository.deleteByClientId(clientId);
        }else {
            throw new EntityNotFoundException();
        }
    }

}
