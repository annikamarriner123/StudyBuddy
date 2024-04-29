package otago.StudyBuddy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import otago.StudyBuddy.domain.Message;
import otago.StudyBuddy.domain.Message.MessageType;

@Component
public class WebSocketEventListener {
    
    private static final Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);
    
    private final SimpMessageSendingOperations messagingTemplate;
    
    @Autowired
    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            log.info("User disconnected: {}", username);
            Message chatMessage = new Message();
            chatMessage.setType(MessageType.LEAVE);
//            chatMessage.setSender(sender);
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
    
}
