/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.domain;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

/**
 *
 * @author willi
 */
@Entity
@Table(name = "Message")
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;
    private Integer senderId;
    private Integer recipientId;
    private String content;
    private LocalDateTime timestamp;
 

    
    public Message (){}
    
    public Message(Integer senderId, Integer recipientId, String content, LocalDateTime timestamp){
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" + "messageId=" + messageId + ", senderId=" + senderId + ", recipientId=" + recipientId + ", content=" + content + ", timestamp=" + timestamp + '}';
    }
    
    
}
