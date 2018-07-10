package com.lynx.oauth.DAO;

import com.lynx.oauth.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findByClientId(String clientId);
}
