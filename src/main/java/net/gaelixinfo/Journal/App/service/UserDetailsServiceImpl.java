package net.gaelixinfo.Journal.App.service;

import net.gaelixinfo.Journal.App.entity.User;
import net.gaelixinfo.Journal.App.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    // Mongo repository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(SimpleGrantedAuthority::new).toList()
        );
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findByUsername(username);
//        if (user == null) {
//
//            return org.springframework.security.core.userdetails.User.builder()
//                     .username(user.getUsername())
//                     .password(user.getPassword())
//                     .roles(user.getRoles().toArray(new String[0]))
//                    .passwordEncoder(passwordEncoder)
//                     .build();
//        }
//
//        throw  new UsernameNotFoundException("User not found with username: " + username);
//    }


}
