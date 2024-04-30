package otago.StudyBuddy.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import otago.StudyBuddy.domain.ChatRoom;
import otago.StudyBuddy.domain.Message;
import otago.StudyBuddy.domain.MessagePayload;
import otago.StudyBuddy.service.ChatRoomService;
import otago.StudyBuddy.service.MessageService;

@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload MessagePayload payload) {
        return messageService.sendMessage(payload);
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
