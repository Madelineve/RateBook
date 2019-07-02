package com.magda.projekt.configuration;

import com.magda.projekt.dao.userDao;
import com.magda.projekt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthenticationDetails implements UserDetailsService {
    @Autowired
    private userDao dao;
    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User user = dao.findByLogin(login);

        if (user != null) {
            List< GrantedAuthority> grupa = new ArrayList< GrantedAuthority>();
            grupa.add(new SimpleGrantedAuthority("normalUser"));
            return new
                    org.springframework.security.core.userdetails.User(user.getLogin(),
                    user.getPassword(), true,
                    true, true, true, grupa);
        } else {
            throw new UsernameNotFoundException("Zły login lub hasło...");
        }
    }
}
