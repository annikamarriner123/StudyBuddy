/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.service;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otago.StudyBuddy.domain.ChatRoom;
import otago.StudyBuddy.domain.Message;
import otago.StudyBuddy.domain.MessagePayload;
import otago.StudyBuddy.domain.User;
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

    public Message sendMessage(MessagePayload payload) {
        User user = userRepository.findByUserId(payload.getSenderId())
                .orElse(null);
        ChatRoom chatRoom = chatRoomRepository.findByChatRoomId(payload.getChatroomId());

        Message message = new Message();
        message.setSender(user);
        message.setChatroom(chatRoom);
        message.setContent(payload.getContent());
        message.setType(payload.getType());
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));

        return messageRepository.save(message);
    }

    public Message addUser(MessagePayload payload) {
        // Fetch the user using userId
        User user = userRepository.findById(payload.getSenderId())
                .orElse(null);

        // Optional: Manage chatroom logic, if necessary
        ChatRoom chatRoom = null;
        if (payload.getChatroomId() != null) {
            chatRoom = chatRoomRepository.findById(payload.getChatroomId())
                    .orElseThrow(() -> new RuntimeException("Chat room not found with ID: " + payload.getChatroomId()));
            // Add user to chatroom if not already a member
            if (!chatRoom.getUsers().contains(user)) {
                chatRoom.getUsers().add(user);
                chatRoomRepository.save(chatRoom);
            }
        }

        // Create a join message
        Message joinMessage = new Message();
        joinMessage.setSender(user);
        joinMessage.setChatroom(chatRoom);
        joinMessage.setContent(user.getFirstName() + " joined the chat");
        joinMessage.setType(Message.MessageType.JOIN);
        joinMessage.setTimestamp(new Timestamp(System.currentTimeMillis()));

        // Save the join message
        messageRepository.save(joinMessage);

        return joinMessage;
    }
}
