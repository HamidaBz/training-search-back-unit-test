package com.memoire.trainingSite.security;
/*
import com.memoire.trainingSite.DAO.SiteUserRepo;
import com.memoire.trainingSite.models.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class UDService implements UserDetailsService {

    private SiteUserRepo userRepo ;
    @Autowired
    public UDService(SiteUserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SiteUser user = userRepo.findByUsername(username);
        if(user == null){
            System.err.println("user not found");
        }else {
            System.err.println("user  found");
        }

        assert user != null;
        return new User(user.getUsername(), user.getUser_password() , List.of(new SimpleGrantedAuthority("ADMIN")));
    }

}
*/