/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.service;

import java.util.Collection;
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
    
     private final UserRepository userRepository;

    @Autowired
    public PaperService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
      public User addUserPapers(Integer userId, Collection<String> papers) {
        User user = userRepository.findById(userId).orElse(null); 
        if (user != null && papers != null) {
            // Check each paper code format before adding to user
            for (String paper : papers) {
                if (!isValidPaperFormat(paper)) {
                    // If the format is invalid, return null (or handle the error as needed)
                    return null;
                }
            }
            // Update the user's papers using the setPapers method
            user.setPapers(papers); 
            // Save the updated user object in the repository
            return userRepository.saveAndFlush(user); 
        }
        return null;
    }
    
    
    private boolean isValidPaperFormat(String paper) {
        // Define the pattern for valid paper code format "CCC###" or "cc###"
        String pattern = "^[A-Z]{3}\\d{3}$|^[a-z]{2}\\d{3}$";
        // Check if the paper code matches the pattern
        return Pattern.matches(pattern, paper);
    }
    
}

