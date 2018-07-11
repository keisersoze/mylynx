package com.lynx.oauth.DAO;

import com.lynx.oauth.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    void deleteByUsername(String username);
    boolean existsByUsername(String username);
}