/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import otago.StudyBuddy.domain.User;

/**
 *
 * @author glenm
 */
@Repository
@Component
public interface UserRepository extends JpaRepository<User, Integer> {
    
    //Querying user using username and password
    Optional<User> findByUsernameAndPassword(String username, String password);   
    
    Optional<User> findById(Integer userId);
  
    }