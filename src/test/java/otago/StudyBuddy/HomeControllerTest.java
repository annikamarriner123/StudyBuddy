/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 *
 * @author Glen G
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetStartPage() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser(username = "John", roles = {"User"})
    public void testGetChatPage() throws Exception {
        mockMvc.perform(get("/chat"))
               .andExpect(status().isOk())
               .andExpect(view().name("chat"));
    }

    @Test
    @WithMockUser(username = "John", roles = {"User"})
    public void testGetTeamPage() throws Exception {
        mockMvc.perform(get("/team"))
               .andExpect(status().isOk())
               .andExpect(view().name("team"));
    }

    @Test
    @WithMockUser(username = "John", roles = {"User"})
    public void testGetSettingsPage() throws Exception {
        mockMvc.perform(get("/settings"))
               .andExpect(status().isOk())
               .andExpect(view().name("settings"));
    }

//    @Test
//    @WithMockUser(username = "John", roles = {"User"})
//    public void testGetAboutStudentPage() throws Exception {
//        mockMvc.perform(get("/aboutStudent"))
//               .andExpect(status().isOk())
//               .andExpect(view().name("aboutStudent"));
//    }

    @Test
    @WithMockUser(username = "John", roles = {"User"})
    public void testGetPreferencesPage() throws Exception {
        mockMvc.perform(get("/preferences"))
               .andExpect(status().isOk())
               .andExpect(view().name("preferences"));
    }
}
