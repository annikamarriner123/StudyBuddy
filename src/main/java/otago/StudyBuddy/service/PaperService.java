/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.service;

import jakarta.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otago.StudyBuddy.domain.User;
import otago.StudyBuddy.domain.Paper;
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

    @Transactional
    public User addUserPapers(Integer userId, Collection<Paper> papers) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null && papers != null) {
            for (Paper paper : papers) {
                if (!isValidPaperFormat(paper.getPaperCode())) {
                    // If any paper has an invalid format, return null
                    return null;
                }
                // Initialize the users set if it's null
                if (paper.getUsers() == null) {
                    paper.setUsers(new HashSet<>());
                }
                // Set the user for each paper
                paper.getUsers().add(user);
            }
            // Add the papers to the user's collection
            user.getPapers().addAll(papers);
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
