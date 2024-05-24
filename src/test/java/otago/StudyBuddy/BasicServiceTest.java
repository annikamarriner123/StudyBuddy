/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import otago.StudyBuddy.service.ChatRoomService;
import otago.StudyBuddy.service.MessageService;
import otago.StudyBuddy.service.UserService;

/**
 *
 * @author willi
 * This test is designed to test that the service classes are in fact not retruning null
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BasicServiceTest {

    @MockBean
    private MessageService messageService;

    @MockBean
    private ChatRoomService chatRoomService;

    @MockBean
    private UserService userService;

    @Autowired
    private ApplicationContext context;

    //Tests the beans are acutally not null a very basic test
    @Test
    public void testBeanLoading() {
        assertThat(context.getBean(MessageService.class)).isNotNull();
        assertThat(context.getBean(ChatRoomService.class)).isNotNull();
        assertThat(context.getBean(UserService.class)).isNotNull();
    }
}
