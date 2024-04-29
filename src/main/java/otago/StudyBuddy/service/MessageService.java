/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otago.StudyBuddy.domain.Message;
import otago.StudyBuddy.repository.MessageRepository;

/**
 *
 * @author willi
 */
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessagesForChatRoom(Integer chatId) {
        return messageRepository.findByChatRoom_ChatIdOrderByTimestamp(chatId);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}
