/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.TodoSite.services;

import com.example.TodoSite.domain.User;
import com.example.TodoSite.domain.UserRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        User user  = userRepo.findByEmail(email);
        CustomUserDetails userDetails = null;
        
        if(user != null){
            userDetails = new CustomUserDetails();
            //System.out.println(user);
            userDetails.setUser(user);
        }else {
            throw  new UsernameNotFoundException("user with email address " + email + "does not exist");
        }
        
        return userDetails;
    }

    
    
}
