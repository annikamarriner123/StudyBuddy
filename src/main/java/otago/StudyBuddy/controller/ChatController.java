package otago.StudyBuddy.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import otago.StudyBuddy.domain.Message;
import otago.StudyBuddy.domain.User;
import otago.StudyBuddy.service.ChatRoomService;
import otago.StudyBuddy.service.MessageService;
import otago.StudyBuddy.service.UserService;

@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private UserService userService;

    @MessageMapping("/chat.sendMessage/{chatroomId}")
    @SendTo("/topic/chatroom/{chatroomId}")
    public Message sendMessage(@Payload Message message, @DestinationVariable String chatroomId) {
 // Fetch user based on senderId
        User user = userService.getUserById(message.getUserId());
        
        message.setSenderName(user.getUsername());
        // Set chatRoom in message
        message.setChatRoom(chatRoomService.getChatRoomById(Integer.valueOf(chatroomId)));
        
        // Save message and return
        Message savedMessage = messageService.sendMessage(message);
        
        return savedMessage;
    }

    @MessageMapping("/chat.loadMessages/{chatroomId}")
    @SendTo("/topic/chatroom/{chatroomId}/messages")
    public List<Message> loadMessages(@DestinationVariable String chatroomId) {
        return messageService.getMessagesByChatRoomId(Integer.valueOf(chatroomId));
    }

    @MessageMapping("/chat.loadUsers/{chatroomId}")
    @SendTo("/topic/chatroom/{chatroomId}/users")
    public List<User> loadUsers(@DestinationVariable String chatroomId) {
        return userService.getUsersByChatRoomId(Integer.valueOf(chatroomId));
    }

    @GetMapping("/{chatroomId}/messages")
    public List<Message> getMessagesByChatRoomId(@PathVariable Integer chatroomId) {
        return messageService.getMessagesByChatRoomId(chatroomId);
    }

}
