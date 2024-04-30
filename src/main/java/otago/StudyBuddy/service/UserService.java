/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import otago.StudyBuddy.domain.User;
import otago.StudyBuddy.repository.UserRepository;

/**
 *
 * @author glenm
 */
@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String firstName, String surname, String username, String password, String email) {
        if (firstName != null && surname != null && username != null && password != null && email != null) {
            User newUser = new User();
            newUser.setFirstName(firstName);
            newUser.setSurname(surname);
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setEmail(email);
            return userRepository.save(newUser);

        }
        return null;
    }

    public User logInUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }

//    public UserDetails getLoggedInUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            Object principal = authentication.getPrincipal();
//            if (principal instanceof User) {
//                return (User) principal;
//            }
//        }
//        return null;
//    }
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByUsername(username).orElse(null);
        return user;
    }

}
