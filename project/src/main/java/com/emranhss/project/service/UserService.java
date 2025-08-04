package com.emranhss.project.service;


import com.emranhss.project.repository.IUserRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import com.emranhss.project.entity.User;


import java.util.Collections;
import java.util.List;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private IUserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        );

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );


    }



}
