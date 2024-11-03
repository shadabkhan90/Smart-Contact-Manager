package com.scm.Services.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.Repositories.UserRepo;

@Service
public class SecurityCustomUserDeatilsServies implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Apne user ko load kararey hai
       return userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

}
