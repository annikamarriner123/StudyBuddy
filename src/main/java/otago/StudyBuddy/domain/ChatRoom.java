/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.domain;

import java.util.Collection;

/**
 *
 * @author willi
 */
public class ChatRoom {

    private Integer chatId;
    private Collection<Message> messages;
    private Integer senderId;
    private Collection<User> recipientUsers;
    private String chatName;

    //default constructor
    public ChatRoom() {}

    /**
     * A chat room will store collections of messages even if there is only 2
     * people (1 on 1) it will be considered a chatroom.
     */
    public ChatRoom(Integer chatId, Collection<Message> messages, Integer senderId, Collection<User> recipientUsers, String chatName) {
       
        this.chatId = chatId;
        this.messages = messages;
        this.senderId = senderId;
        this.recipientUsers = recipientUsers;
        this.chatName = chatName;
    }

    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Collection<User> getRecipientUsers() {
        return recipientUsers;
    }

    public void setRecipientUsers(Collection<User> recipientUsers) {
        this.recipientUsers = recipientUsers;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }
    
    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

}
