/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.domain;

<<<<<<< Updated upstream
=======
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
>>>>>>> Stashed changes
import java.sql.Timestamp;
import jakarta.persistence.*;

/**
 *
 * @author willi
 */
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer messageId;

<<<<<<< Updated upstream
    @Column(name = "content")
=======
    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatroom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User sender;

>>>>>>> Stashed changes
    private String content;

    @Column(name = "timestamp")
    private Timestamp timestamp;

<<<<<<< Updated upstream
    @ManyToOne
    @JoinColumn(name = "chat_id", referencedColumnName = "chat_id")
    private ChatRoom chatRoom;
=======
    public Message() {
    }
>>>>>>> Stashed changes

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    private User sender;

    //Default
    public Message(){}
    
    public Message( String content, Timestamp timestamp, ChatRoom chatRoom, User sender) {
        this.content = content;
        this.timestamp = timestamp;
        this.chatRoom = chatRoom;
        this.sender = sender;
    }
    
    //Getters and setters
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
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

<<<<<<< Updated upstream
    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
=======
    public String getSenderName() {
        return sender != null ? sender.getUsername() : "Unknown";
>>>>>>> Stashed changes
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
    
    
<<<<<<< Updated upstream
    
    

=======

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
>>>>>>> Stashed changes
}
