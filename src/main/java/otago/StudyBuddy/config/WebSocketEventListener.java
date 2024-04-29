package otago.StudyBuddy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import otago.StudyBuddy.domain.Message;
import otago.StudyBuddy.domain.ChatRoom;
import otago.StudyBuddy.domain.User;
import java.sql.Timestamp;
import otago.StudyBuddy.repository.ChatRoomRepository;
import otago.StudyBuddy.repository.UserRepository;

@Component
public class WebSocketEventListener {

    private static final Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public WebSocketEventListener() {
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        Integer userId = (Integer) headerAccessor.getSessionAttributes().get("user_id");

        if (userId != null) {
            User user = findUserByUserId(userId);
            String username = user.getFirstName();
            ChatRoom chatRoom = findChatRoomForUser(user);
            
            log.info("User Disconnected : " + username);
            
            if (user != null && chatRoom != null) {
                Message leaveMessage = new Message();
                leaveMessage.setSender(user);
                leaveMessage.setChatroom(chatRoom);
                leaveMessage.setContent(username + " has left the chat");
                leaveMessage.setTimestamp(new Timestamp(System.currentTimeMillis()));

                // Send this message to the chatroom's topic
                messagingTemplate.convertAndSend("/topic/" + chatRoom.getChatRoomId(), leaveMessage);
            }
        }
    }

    private User findUserByUserId(Integer userId) {
        return userRepository.findUserByUserId(userId)
                .orElse(null); 
    }

    private ChatRoom findChatRoomForUser(User user) {
        return chatRoomRepository.findByUsersContains(user)
                .orElse(null); 
    }

}
