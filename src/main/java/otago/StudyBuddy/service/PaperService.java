/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.service;

import jakarta.transaction.Transactional;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otago.StudyBuddy.domain.User;
import otago.StudyBuddy.repository.UserRepository;

/**
 *
 * @author jasminebroughton
 */
@Service
public class PaperService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    @Autowired
    public PaperService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Transactional
    public User addUserPapers(Integer userId, String papers) {
        User user = userRepository.findByUserId(userId).orElse(null);
        if (user != null && papers != null) {

            user.setPapers(papers);
            // Save the updated user object in the repository
            return userRepository.saveAndFlush(user);
        }
        return null;
    }

    private boolean isValidPaperFormat(String paperCode) {
        // Define the pattern for valid paper code format "CCC###" or "cc###"
        String pattern = "^[A-Z]{3}\\\\d{3}$|^[a-z]{2}\\\\d{3}$";
        // Check if the paper code matches the pattern
        return Pattern.matches(pattern, paperCode);
    }
}
