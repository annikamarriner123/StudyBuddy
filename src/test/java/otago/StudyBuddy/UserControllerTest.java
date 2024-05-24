/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import otago.StudyBuddy.domain.User;
import otago.StudyBuddy.service.PaperService;
import otago.StudyBuddy.service.UserService;

/**
 *
 * @author Glen G
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private PaperService paperService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void testGetSignUpPage() throws Exception {
        mockMvc.perform(get("/sign-up"))
                .andExpect(status().isOk())
                .andExpect(view().name("sign-up"));
    }

    @Test
    public void testSignUp() throws Exception {
        Mockito.when(userService.registerUser(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(new User());

        mockMvc.perform(post("/sign-up")
                .param("firstName", "John")
                .param("surname", "Doe")
                .param("username", "john.doe")
                .param("password", "password")
                .param("email", "john.doe@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/log-in"));
    }

    @Test
    public void testGetLoginPage() throws Exception {
        mockMvc.perform(get("/log-in"))
                .andExpect(status().isOk())
                .andExpect(view().name("log-in"));
    }

    @Test
    @WithMockUser(username = "John", roles = {"User"})
    public void testLogInPost() throws Exception {
        User user = new User();
        user.setUsername("john.doe");
        user.setPassword("password");

        User storedUser = new User();
        storedUser.setUsername("john.doe");
        storedUser.setPassword("encodedPassword");
        Mockito.when(userService.logInUser(Mockito.any(), Mockito.any())).thenReturn(storedUser);
        Mockito.when(passwordEncoder.matches(Mockito.any(), Mockito.any())).thenReturn(true);

        mockMvc.perform(post("/log-in")
                .param("username", "John")
                .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/log-in?error"));
    }

    @Test
    @WithMockUser(username = "John", roles = {"User"})
    public void testGetUpdatePapers() throws Exception {

        Mockito.when(userService.getCurrentUser()).thenReturn(new User());

        mockMvc.perform(get("/updatePapers"))
                .andExpect(status().isOk())
                .andExpect(view().name("updatePapers"));
    }

    @Test
    @WithMockUser(username = "John", roles = {"User"})
    public void testPostUpdatePapers() throws Exception {
        User testUser = new User();
        testUser.setUserId(1);
        Mockito.when(userService.getCurrentUser()).thenReturn(testUser);
        Mockito.when(paperService.addUserPapers(Mockito.any(), Mockito.anyList())).thenReturn(new User());
        mockMvc.perform(post("/updatePapers")
                .param("paperCodes", "COSC344,INFO310"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/updatePapers"));
    }

    @Test
    @WithMockUser(username = "John", roles = {"User"})
    public void testPostUpdateUserDetails() throws Exception {

        User testUser = new User();
        testUser.setUserId(1);
        testUser.setUsername("testUsername");
        testUser.setEmail("test@example.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(testUser);
        Mockito.when(userService.updateUserDetails(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(testUser);
        mockMvc.perform(post("/update-details")
                .contentType("application/json")
                .content(userJson))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/update-details"));
    }

    @Test
    @WithMockUser(username = "John", roles = {"User"})
    public void testGetFindStudyPeersPage() throws Exception {

        mockMvc.perform(get("/findStudyPeers"))
                .andExpect(status().isOk())
                .andExpect(view().name("findStudyPeers"));
    }

    @Test
    @WithMockUser(username = "John", roles = {"User"})
    public void testPostFindStudyPeersPage() throws Exception {
        List<User> testUserList = new ArrayList<>();
        Mockito.when(userService.getUsersByPaper(Mockito.any()))
                .thenReturn(testUserList);
        mockMvc.perform(post("/findStudyPeers")
                .param("paper", "INFO310"))
                .andExpect(status().isOk())
                .andExpect(view().name("findStudyPeers"));
    }

}
