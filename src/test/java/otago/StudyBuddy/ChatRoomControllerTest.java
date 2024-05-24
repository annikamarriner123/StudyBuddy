/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import otago.StudyBuddy.domain.ChatRoom;
import otago.StudyBuddy.domain.Message;
import otago.StudyBuddy.service.ChatRoomService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import otago.StudyBuddy.controller.ChatRoomController;


/**
 * This test is a simple test of the ChatRoomController. It creates mocks of the ChatRoomService and perfomrs basic tests same with the controller.
 * @author willi
 */
@SpringBootTest
class ChatRoomControllerTest {

    @Mock
    private ChatRoomService chatRoomService;

    @InjectMocks
    private ChatRoomController chatRoomController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateChatRoom() {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName("Test ChatRoom");
        when(chatRoomService.createChatRoom(anyString())).thenReturn(chatRoom);

        ResponseEntity<ChatRoom> response = chatRoomController.createChatRoom(Map.of("name", "Test ChatRoom"));

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(chatRoom, response.getBody());
    }

    @Test
    void testGetAllChatRooms() {
        List<ChatRoom> chatRooms = Arrays.asList(new ChatRoom(), new ChatRoom());
        when(chatRoomService.getAllChatRooms()).thenReturn(chatRooms);

        ResponseEntity<List<ChatRoom>> response = chatRoomController.getAllChatRooms();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(chatRooms, response.getBody());
    }

    @Test
    void testDeleteChatRoom() {
        doNothing().when(chatRoomService).deleteChatRoom(anyInt());

        ResponseEntity<Void> response = chatRoomController.deleteChatRoom(1);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(chatRoomService, times(1)).deleteChatRoom(1);
    }

    @Test
    void testGetMessages() {
        List<Message> messages = Arrays.asList(new Message(), new Message());
        when(chatRoomService.getMessages(anyInt())).thenReturn(messages);

        ResponseEntity<List<Message>> response = chatRoomController.getMessages(1);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(messages, response.getBody());
    }

    @Test
    void testGetMessagesNotFound() {
        when(chatRoomService.getMessages(anyInt())).thenReturn(null);

        ResponseEntity<List<Message>> response = chatRoomController.getMessages(1);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
    }
}
