/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otago.StudyBuddy.domain.Message;
import otago.StudyBuddy.domain.User;
import otago.StudyBuddy.repository.MessageRepository;
import otago.StudyBuddy.repository.UserRepository;

// /**
//  *
//  * @author willi
//  */
@Service
public class MessageService {

    @Autowired
    public MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    
    @Autowired
    private UserRepository userRepository;

    public void processMessage(Message message) {
        User user = userRepository.findById(message.getSenderId())
                        .orElse(null);
        Message message1 = new Message();
        message.setSender(user);
        message.setContent(message.getContent());
        message.setType(message.getType());
        messageRepository.save(message1);
    }

}
