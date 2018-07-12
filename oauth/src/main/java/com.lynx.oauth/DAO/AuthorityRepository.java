package com.lynx.oauth.DAO;

import com.lynx.oauth.model.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority,Long> {
    Authority findByName(String name);
    void deleteByName(String name);
    boolean existsByName(String name);
}
