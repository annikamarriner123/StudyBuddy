/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.service;

import java.util.Collection;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otago.StudyBuddy.domain.User;
import otago.StudyBuddy.repository.UserRepository;

/**
 *
 * @author glenm
 */
@Service
public class MatchService {
    
    @Autowired
    public UserRepository userRepository;

    public MatchService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
    public Collection<User> matchUsers(Integer userId) {
        Collection<User> usersMatched = new HashSet<>();
        User userForMatching = userRepository.findById(userId).orElse(null);
        if(userForMatching != null) {
           //Collection<String> userPapers = userForMatching.getPapers();
           String userMajor = userForMatching.getMajor();
           
           //usersMatched = userRepository.findByMatching(major, papers).orElse(null);
            return null;
        }
        return null;
    }
}
