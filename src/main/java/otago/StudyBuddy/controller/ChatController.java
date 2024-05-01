/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.controller;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import otago.StudyBuddy.domain.Message;
import otago.StudyBuddy.domain.MessagePayload;
import otago.StudyBuddy.domain.User;
import otago.StudyBuddy.service.ChatRoomService;
import otago.StudyBuddy.service.MessageService;
import otago.StudyBuddy.service.UserService;

/**
 *
 * @author willi
 */
@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private UserService userService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(MessagePayload payload) {
        User currentUser = userService.getCurrentUser(); 
        System.out.println(currentUser.getUserId());
        Message message = new Message();
        
        message.setContent(payload.getContent());
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));
        message.setSender(currentUser);
        message.setType(Message.MessageType.CHAT);  

       // messageService.save(message); //TODO implement a save in message service just save message to db
        return message;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload MessagePayload payload, SimpMessageHeaderAccessor headerAccessor) {
        Message chatMessage = messageService.addUser(payload);
        // Assuming addUser in your service sets the user and returns the message.
        // This example also assumes you modify your payload or service to handle user fetching.
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender().getFirstName());
        return chatMessage;
    }

    @GetMapping("/chatrooms")
    public ResponseEntity<List<ChatRoom>> getAllChatRooms() {
        List<ChatRoom> chatRooms = chatRoomService.findAllChatRooms();
        return ResponseEntity.ok(chatRooms);
    }

}

//A room will be at minium two people there will be a room id which seperates them 
//the chat room will have recipient id/s and sender id sender id will be the logged in user 
//recipient id will be done by mathcing userId? username? first and lastname?
//nned to also incldue notifications focus on email notification based on the users email should be able to be turned off/on
//each message in the chat room will habe its own unique message Id for that chat room 

