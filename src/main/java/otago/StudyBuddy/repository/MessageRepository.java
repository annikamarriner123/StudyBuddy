/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otago.StudyBuddy.domain.Message;
import java.util.List;

/**
 *
 * @author willi
 */


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByChatRoom_ChatIdOrderByTimestamp(Integer chatId);
}
