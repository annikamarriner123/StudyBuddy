/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.domain;

import otago.StudyBuddy.domain.Message.MessageType;

/**
 * THis class it to createe a message rom the json data as its unable to handle objects using the sender id I can create a message from there 
 * @author willi
 */
public class MessagePayload {

    private Integer senderId;
    private Integer chatroomId;
    private String content;
    private MessageType type;

    public MessagePayload() {
    }

    public MessagePayload(Integer senderId, Integer chatroomId, String content, MessageType type) {
        this.senderId = senderId;
        this.chatroomId = chatroomId;
        this.content = content;
        this.type = type;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(Integer chatroomId) {
        this.chatroomId = chatroomId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

}
