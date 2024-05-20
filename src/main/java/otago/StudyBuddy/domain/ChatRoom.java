package otago.StudyBuddy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    
    @JsonIgnore
    @ManyToMany(mappedBy = "chatRooms")
    private Set<User> users = new HashSet<>();

    public ChatRoom() {}

    public ChatRoom(Integer chatRoomId, String name) {
        this.chatRoomId = chatRoomId;
        this.name = name;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
