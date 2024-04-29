package otago.StudyBuddy.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import otago.StudyBuddy.domain.Message;
import otago.StudyBuddy.service.MessageService;

@Controller
public class MessageController {

    private final MessageService messageService;

    // Constructor-based dependency injection
    public MessageController(MessageService messageService) {
        this.messageService = messageService;     
    }
   
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message chatMessage) {
        return messageService.save(chatMessage); // Assuming you might want to save messages
    }
    
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Store username in session attributes
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSenderName());
        chatMessage.setContent(chatMessage.getSenderName() + " joined the chat");
        return messageService.save(chatMessage); // Assuming you might want to save or modify messages
    }
}
