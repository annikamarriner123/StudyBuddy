package otago.StudyBuddy.domain;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "chat_rooms")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatId;

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    private Collection<Message> messages;

    private Integer senderId;  // This might need clarification or change

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<User> recipientUsers;

    @Column(length = 100)
    private String chatName;

    public ChatRoom() {}

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
