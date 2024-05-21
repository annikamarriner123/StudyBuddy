/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otago.StudyBuddy.domain.ChatRoom;
import otago.StudyBuddy.domain.Message;
import otago.StudyBuddy.repository.ChatRoomRepository;
import otago.StudyBuddy.repository.MessageRepository;

/**
 *
 * @author willi
 */
@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    
    
    @Autowired
    private MessageRepository messageRepository;

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom getChatRoomById(Integer chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElse(null);
        return chatRoom;
    }

    public ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(name);
        return chatRoomRepository.save(chatRoom);
    }

    public void deleteChatRoom(Integer chatRoomId) {
        chatRoomRepository.deleteById(chatRoomId);
    }

    public List<Message> getMessages(Integer chatRoomId) {
        return messageRepository.findByChatRoom_ChatRoomId(chatRoomId);
    }

}
