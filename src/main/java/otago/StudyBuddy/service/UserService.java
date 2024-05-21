/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
            List<String> userPapers = new ArrayList<>();
            for(int i = 0; i < 8; i++) userPapers.add("");
            newUser.setUserPapers(userPapers);
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
    
    //this will update user's details
    public User updateUserDetails(String firstName, String surname, String major, String email) {
       User currentUser = getCurrentUser();
       
       currentUser.setFirstName(firstName);
       currentUser.setSurname(surname);
       currentUser.setMajor(major);
       currentUser.setEmail(email);
       return userRepository.saveAndFlush(currentUser);
    }
    
    public User getUserById(Integer userId){
        return userRepository.findByUserId(userId).orElse(null);
    }
    
    @Transactional
    public List<User> getUsersByChatRoomId(Integer chatRoomId) {
        return userRepository.findUsersByChatRoomId(chatRoomId);
    }
    
    public List<User> getUsersByPaper(String paper) {
        User currUser = getCurrentUser();
        List<User> users = userRepository.findAll();
        return users.stream()
        .filter(user -> user.getUserPapers() != null)
        .filter(user -> user.getUserPapers().contains(paper))
        .filter(user -> user.getUserId() != currUser.getUserId())
        .toList();
    }

}
