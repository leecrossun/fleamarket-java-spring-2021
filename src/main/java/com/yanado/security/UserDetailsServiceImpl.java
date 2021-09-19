package com.yanado.security;

import com.yanado.dao.UserDAO;
import com.yanado.dto.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource(name="userDAO")
    private UserDAO userDAO;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.getUserByUserName(s);
        return new UserDetailsImpl(user);
    }

}