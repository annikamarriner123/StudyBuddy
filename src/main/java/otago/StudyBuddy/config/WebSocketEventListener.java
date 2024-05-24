package otago.StudyBuddy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
public class WebSocketEventListener {
    
    private static final Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);
    
    private final SimpMessageSendingOperations messagingTemplate;
    
    @Autowired
    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    
    
}
