package otago.StudyBuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getStartPage() {
        return "index";
    }

    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/findStudyPeers")
    public String getFindStudyPeersPage() {
        return "findStudyPeers";
    }

    @GetMapping("/chat")
    public String getChatPay() {
        return "chat";
    }

    @GetMapping("/team")
    public String getTeam() {
        return "team";
    }

    @GetMapping("/settings")
    public String getSettings() {
        return "settings";
    }

    @GetMapping("/addPaper")
    public String getAddPapers() {
        return "addPaper";
    }

    @GetMapping("/aboutStudent")
    public String getAboutStudent() {
        return "aboutStudent";
    }

    @GetMapping("/preferences")
    public String getPreferences() {
        return "preferences";
    }



}
