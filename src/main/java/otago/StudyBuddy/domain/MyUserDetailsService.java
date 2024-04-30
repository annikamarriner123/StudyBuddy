/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.domain;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.regex;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import otago.StudyBuddy.repository.UserRepository;

/**
 *
 * @author Glen G
 */
@Service
public class MyUserDetailsService implements UserDetailsService{
    
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<otago.StudyBuddy.domain.User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
        return User.builder()
                .username(user.get().getUsername())
                .password(user.get().getPassword())
                .roles(getRoles(user.get()))
                .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
    
    //get user roles and add to User.builder
    //if null defaults to USER else, ADMIN or USER,ADMIN
    public String getRoles(otago.StudyBuddy.domain.User user) {
        if(user.getRole() == null) {
            return "USER";
        }
        else return "USER";

    }
    
}
