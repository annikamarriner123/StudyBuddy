package otago.StudyBuddy.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otago.StudyBuddy.domain.ChatRoom;
import otago.StudyBuddy.domain.Message;
import otago.StudyBuddy.service.ChatRoomService;

@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        ChatRoom chatRoom = chatRoomService.createChatRoom(name);
        return ResponseEntity.ok(chatRoom);
    }

    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllChatRooms() {
        List<ChatRoom> chatRooms = chatRoomService.getAllChatRooms();
        return ResponseEntity.ok(chatRooms);
    }
    
    @DeleteMapping("/{chatRoomId}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable Integer chatRoomId){
        chatRoomService.deleteChatRoom(chatRoomId);
        return ResponseEntity.noContent().build();
    }
    
  @GetMapping("/{chatRoomId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable Integer chatRoomId) {
        List<Message> messages = chatRoomService.getMessages(chatRoomId);
        if (messages != null) {
            return ResponseEntity.ok(messages);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
