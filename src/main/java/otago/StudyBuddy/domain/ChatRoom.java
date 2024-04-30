package otago.StudyBuddy.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chat_rooms")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatRoomId;

    private String name;

    @OneToMany(mappedBy = "chatroom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Message> messages = new HashSet<>();
    
    @ManyToMany
    @JoinTable(
            name = "chatroom_user", // Name of the join table
            joinColumns = @JoinColumn(name = "chatroom_id"), // Column referencing this entity (ChatRoom)
            inverseJoinColumns = @JoinColumn(name = "user_id") // Column referencing the associated entity (User)
    )
    private Set<User> users = new HashSet<>(); // Users in the chatroom

    public ChatRoom() {
    }

    public ChatRoom(String name) {
        this.name = name;
    }

    public Integer getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Integer chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    
    
}
