package com.elcom.service;

import com.elcom.model.User;
import com.elcom.auth.CustomUserDetails;
import com.elcom.repository.UserCustomizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthServiceImpl implements UserDetailsService,AuthService {
    @Autowired
    private UserCustomizeRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName)
    {
        User user = userRepository.findByUserName(userName);
        if(user == null)
            throw new UsernameNotFoundException(userName);
        return new CustomUserDetails(user);
    }
    @Transactional
    @Override
    public UserDetails loadUserById(Long userID) {
        User user = userRepository.findById(userID);
        if (user == null)
            throw new UsernameNotFoundException("User not found with id : " + userID);
        return new CustomUserDetails(user);
    }
}
