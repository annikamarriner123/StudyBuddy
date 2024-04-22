/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.domain;

import java.sql.Timestamp;

/**
 *
 * @author willi
 */
public class Message {
    private Integer messageId;
    private String content;
    private Timestamp timestamp;

    
    public Message (){}
    
    public Message(Integer messageId, String content, Timestamp timestamp){
        this.messageId = messageId;
        this.content = content;
        this.timestamp = timestamp;
    }

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

    @Override
    public String toString() {
        return "Message{" + "messageId=" + messageId + ", content=" + content + ", timestamp=" + timestamp + '}';
    }
    
    
}
