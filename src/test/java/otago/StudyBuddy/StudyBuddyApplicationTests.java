package otago.StudyBuddy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import otago.StudyBuddy.service.UserService;

@SpringBootTest
class StudyBuddyApplicationTests {

    @Autowired
    private UserService userService;

    
	@Test
	void contextLoads() {
		assertThat(userService).isNotNull();
	}
        

}
