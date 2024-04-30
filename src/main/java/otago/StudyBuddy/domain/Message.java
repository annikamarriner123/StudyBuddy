package otago.StudyBuddy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
public class Message {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatroom;

    private String content;
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User sender;

    private MessageType type;

    public Message() {
    }

    public Message(String content, Timestamp timestamp, ChatRoom chatroom) {
        this.content = content;
        this.timestamp = timestamp;
        this.chatroom = chatroom;
    }

    // Getters and Setters
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public ChatRoom getChatroom() {
        return chatroom;
    }

    public void setChatroom(ChatRoom chatroom) {
        this.chatroom = chatroom;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
    
        public Integer getSenderId() {
        if (sender != null) {
            return sender.getUserId();
        }
        return null; 
    }

    // Enum for Message Type
    public enum MessageType {
        CHAT,
        LEAVE,
        JOIN
    }
}
