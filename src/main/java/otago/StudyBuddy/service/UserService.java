/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.service;

import org.springframework.stereotype.Service;
import otago.StudyBuddy.domain.User;

/**
 *
 * @author glenm
 */
@Service
public class UserService {
    
    public User registerUser(String firstName, String surname, String username, String password, String email) {
        
        return new User();
    }
}
