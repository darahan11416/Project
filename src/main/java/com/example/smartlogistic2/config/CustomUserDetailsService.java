package com.example.smartlogistic2.config;

import com.example.smartlogistic2.entity.User;
import com.example.smartlogistic2.service.UserService;
import com.example.smartlogistic2.utility.Constants;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService
{
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

   User user = this.userService.getUserByEmailAndStatus(email, Constants.ActiveStatus.ACTIVE.value());

    CustomUserDetails customUserDetails  = new CustomUserDetails(user);

        return customUserDetails;
    }
}
