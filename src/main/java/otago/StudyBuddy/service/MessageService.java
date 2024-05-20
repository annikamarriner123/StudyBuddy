/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.service;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otago.StudyBuddy.domain.Message;
import otago.StudyBuddy.repository.ChatRoomRepository;
import otago.StudyBuddy.repository.MessageRepository;
import otago.StudyBuddy.repository.UserRepository;

// /**
//  *
//  * @author willi
//  */
@Service
public class MessageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

     @Transactional
    public List<Message> getMessagesByChatRoomId(Integer chatRoomId){
        return messageRepository.findByChatRoom_ChatRoomId(chatRoomId);
    }
    
    
    
}
