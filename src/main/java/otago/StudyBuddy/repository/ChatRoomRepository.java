/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import otago.StudyBuddy.domain.ChatRoom;

/**
 *
 * @author willi
 */
@Repository
@Component
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer>{ 
    ChatRoom findByChatRoomId(Integer ChatRoomId);
    
}
