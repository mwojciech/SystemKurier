package com.prz.systemkurier.service.impl;

import com.prz.systemkurier.enumerate.RoleName;
import com.prz.systemkurier.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String indexNumber) throws UsernameNotFoundException {

        UserDetails userDetails = null;
        try {
            com.prz.systemkurier.domain.User userData = userRepository.getByCredentials("KURIER", "KURIER");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDetails;
    }

    private List<GrantedAuthority> getAuthirities(RoleName name) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (name.equals(RoleName.ADMIN)) {
            authorities.add(new SimpleGrantedAuthority(RoleName.ADMIN.getRoleName()));
        } else if (name.equals(RoleName.KURIER)) {
            authorities.add(new SimpleGrantedAuthority(RoleName.KURIER.getRoleName()));
        }

        return authorities;

    }
}
