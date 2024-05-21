/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    Optional<User> findByUsername(String username);

    //Querying user using username and password
    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUserId(Integer userId);

    Optional<User> findByPapers(String paper);

    List<User> findAll();
    
    @Query("SELECT u FROM User u JOIN u.chatRooms c WHERE c.chatRoomId = :chatRoomId")
    List<User> findUsersByChatRoomId(Integer chatRoomId);

}
