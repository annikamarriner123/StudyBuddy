/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;


/**
 *
 * @author willi
 * This test was meant to test the websocket but i cant figure out how to mock the socket so it will fail everytime
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ChatControllerWebSocketTest {

    private WebSocketStompClient stompClient;
    private BlockingQueue<String> blockingQueue;
    private String URL = "ws://localhost:8081/ws";

    @BeforeEach
    public void setup() {
        blockingQueue = new LinkedBlockingDeque<>();
        stompClient = new WebSocketStompClient(new SockJsClient(
            List.of(new WebSocketTransport(new StandardWebSocketClient()))
        ));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    }

    @Test
    public void testSendMessage() throws Exception {
        StompSession stompSession = stompClient.connect(URL, new StompSessionHandlerAdapter() {}).get(1, TimeUnit.SECONDS);

        stompSession.subscribe("/topic/chatroom/1", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return String.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                blockingQueue.offer((String) payload);
            }
        });

        stompSession.send("/app/chat.sendMessage/1", "Test message");

        String receivedMessage = blockingQueue.poll(5, TimeUnit.SECONDS);
        assertThat(receivedMessage).isNotNull();
    }
}




