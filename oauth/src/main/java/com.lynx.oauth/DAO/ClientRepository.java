package com.lynx.oauth.DAO;

import com.lynx.oauth.model.Client;
import com.lynx.oauth.model.User;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
    User findByClientId(String username);
}
