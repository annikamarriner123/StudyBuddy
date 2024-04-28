/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import otago.StudyBuddy.domain.Message;
/**
 *
 * @author willi
 */
@Repository
@Component
public interface MessageRepository extends JpaRepository<Message, Integer>{
    //needs to fetch past messages aswell as send more messages
    
    
}
