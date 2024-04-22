/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.service;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import otago.StudyBuddy.domain.Message;
import otago.StudyBuddy.repository.MessageRepository;

/**
 *
 * @author willi
 */
public class MessageService {
    
    @Autowired
    public MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    
    public MessageService(Integer messageId, String content, Timestamp timestamp){
        if(content != null){
            Message newMessage = new Message();
            newMessage.setContent(content);
        }
    }
    
    
    
}
