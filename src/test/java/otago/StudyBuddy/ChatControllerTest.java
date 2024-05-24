package otago.StudyBuddy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import otago.StudyBuddy.domain.Message;
import otago.StudyBuddy.domain.User;
import otago.StudyBuddy.service.ChatRoomService;
import otago.StudyBuddy.service.MessageService;
import otago.StudyBuddy.service.UserService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import otago.StudyBuddy.controller.ChatController;

/**
 * This test is a simple test of the ChatController. It creates mocks of the ChatService and performs basic tests same with the controller.
 * @author willi
 */
@SpringBootTest
class ChatControllerTest {

    @Mock
    private MessageService messageService;

    @Mock
    private ChatRoomService chatRoomService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ChatController chatController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage() {
        Message message = new Message();
        message.setUserId(1);
        message.setContent("Hello");

        User user = new User();
        user.setUserId(1);
        user.setUsername("TestUser");

        when(userService.getUserById(anyInt())).thenReturn(user);
        when(chatRoomService.getChatRoomById(anyInt())).thenReturn(null); // Assuming getChatRoomById returns null for simplicity
        when(messageService.sendMessage(any(Message.class))).thenReturn(message);

        Message result = chatController.sendMessage(message, "1");

        assertNotNull(result);
        assertEquals("TestUser", result.getSenderName());
        assertEquals("Hello", result.getContent());
        verify(messageService, times(1)).sendMessage(message);
    }

    @Test
    void testLoadMessages() {
        List<Message> messages = Arrays.asList(new Message(), new Message());
        when(messageService.getMessagesByChatRoomId(anyInt())).thenReturn(messages);

        List<Message> result = chatController.loadMessages("1");

        assertNotNull(result);
        assertEquals(messages.size(), result.size());
        verify(messageService, times(1)).getMessagesByChatRoomId(anyInt());
    }

    @Test
    void testLoadUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userService.getUsersByChatRoomId(anyInt())).thenReturn(users);

        List<User> result = chatController.loadUsers("1");

        assertNotNull(result);
        assertEquals(users.size(), result.size());
        verify(userService, times(1)).getUsersByChatRoomId(anyInt());
    }

    @Test
    void testGetMessagesByChatRoomId() {
        List<Message> messages = Arrays.asList(new Message(), new Message());
        when(messageService.getMessagesByChatRoomId(anyInt())).thenReturn(messages);

        List<Message> result = chatController.getMessagesByChatRoomId(1);

        assertNotNull(result);
        assertEquals(messages.size(), result.size());
        verify(messageService, times(1)).getMessagesByChatRoomId(1);
    }
}
