/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.domain;

import jakarta.persistence.*;
import java.util.Collection;

/**
 *
 * @author willi
 */
@Entity
@Table(name = "chat_room")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Integer chatId;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private Collection<Message> messages;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    private User sender;

    @ManyToMany
    @JoinTable(
            name = "chat_room_users",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Collection<User> recipientUsers;

    @Column(name = "chat_name")
    private String chatName;
    
    //Defualt
    public ChatRoom(){}

    public ChatRoom( Collection<Message> messages, User sender, Collection<User> recipientUsers, String chatName) {
        this.messages = messages;
        this.sender = sender;
        this.recipientUsers = recipientUsers;
        this.chatName = chatName;
    }
    
    
    
    
    //Getters n setters
    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
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
    
    
}

