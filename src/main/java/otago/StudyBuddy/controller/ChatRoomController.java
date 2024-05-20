package otago.StudyBuddy.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otago.StudyBuddy.domain.ChatRoom;
import otago.StudyBuddy.service.ChatRoomService;

@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody String name) {
        ChatRoom chatRoom = chatRoomService.createChatRoom(name);
        return ResponseEntity.ok(chatRoom);
    }

    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllChatRooms() {
        List<ChatRoom> chatRooms = chatRoomService.getAllChatRooms();
        return ResponseEntity.ok(chatRooms);
    }
}
