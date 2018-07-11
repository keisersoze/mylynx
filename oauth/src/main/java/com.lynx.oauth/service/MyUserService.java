package com.lynx.oauth.service;

import com.lynx.oauth.DAO.UserRepository;
import com.lynx.oauth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MyUserService implements UserDetailsService {

    @Autowired
    private UserRepository  userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public List<UserDetails> findAll() {
        List<UserDetails> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    public void put(UserDetails userDetails){
        User user = new User(userDetails);
        userRepository.save(user);
    }

    public void deleteByUsername(String username) throws  UsernameNotFoundException{
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
        }else {
            throw new UsernameNotFoundException(username);
        }
    }
}
