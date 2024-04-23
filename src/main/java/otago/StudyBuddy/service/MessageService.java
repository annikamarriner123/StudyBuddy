/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// package otago.StudyBuddy.service;

// import java.sql.Timestamp;
// import org.springframework.beans.factory.annotation.Autowired;
// import otago.StudyBuddy.repository.MessageRepository;

// /**
//  *
//  * @author willi
//  */
// public class MessageService {
    
//     @Autowired
//     public MessageRepository messageRepository;
    
//     public MessageService(Integer messageId,Integer senderId, Integer recipientId, String content, Timestamp timestamp){
//         if(content != null){
//             Message newMessage = new Message();
//             newMessage.setSetSenderId(senderId);
            
//         }
//     }
    
    
    
// }


package otago.StudyBuddy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
    private final List<String> messages = new CopyOnWriteArrayList<>();

    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);  // Ensure the list cannot be modified directly
    }

    public void addMessage(String message) {
        if (message != null && !message.trim().isEmpty()) {
            messages.add(message);
            logger.info("Added message: {}", message);
        } else {
            logger.warn("Attempt to add empty message ignored");
        }
    }

    public boolean removeMessage(String message) {
        boolean removed = messages.remove(message);
        if (removed) {
            logger.info("Removed message: {}", message);
        } else {
            logger.warn("Attempt to remove non-existent message: {}", message);
        }
        return removed;
    }

    public void clearMessages() {
        messages.clear();
        logger.info("All messages have been cleared");
    }
}

