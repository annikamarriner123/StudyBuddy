package otago.StudyBuddy.service;

import java.util.List;
<<<<<<< Updated upstream
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
=======
import org.springframework.stereotype.Service;
import otago.StudyBuddy.domain.ChatRoom;
>>>>>>> Stashed changes
import otago.StudyBuddy.domain.Message;
import otago.StudyBuddy.repository.MessageRepository;

/**
<<<<<<< Updated upstream
 *
 * @author willi
=======
 * Service layer for handling business logic for message operations.
>>>>>>> Stashed changes
 */
@Service
public class MessageService {

<<<<<<< Updated upstream
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessagesForChatRoom(Integer chatId) {
        return messageRepository.findByChatRoom_ChatIdOrderByTimestamp(chatId);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
=======
    private final MessageRepository messageRepository;
    
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Saves a message to the database.
     * @param message the message to save
     * @return the saved message
     */
    public Message save(Message message) {
        return messageRepository.save(message);
    }

     public List<Message> findAllMessages() {
         return messageRepository.findAll();
     }
    

     public List<Message> findMessagesByChatRoom(ChatRoom chatRoom) {
         return messageRepository.findByChatRoom(chatRoom)
                  .orElse(null);
     }
>>>>>>> Stashed changes
}
