/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< Updated upstream
import org.springframework.security.crypto.password.PasswordEncoder;
=======
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
>>>>>>> Stashed changes
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import otago.StudyBuddy.domain.Paper;
import otago.StudyBuddy.domain.User;
import otago.StudyBuddy.service.PaperService;
import otago.StudyBuddy.service.UserService;

/**
 *
 * @author glenm
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PaperService paperService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PaperService paperService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.paperService = paperService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String getSignUpPage(Model model) {
        //this will create a User model that thymeleaf will fill in
        model.addAttribute("signUpRequest", new User());
        return "sign-up";
    }

    @GetMapping("/log-in")
    public String getLogInPage(Model model) {

        model.addAttribute("logInRequest", new User());
        return "log-in";
    }

    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }

    @PostMapping("/sign-up")
    public String register(@ModelAttribute User user) {
        System.out.println(user);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User registeredUser = userService.registerUser(user.getFirstName(), user.getSurname(), user.getUsername(), user.getPassword(), user.getEmail());
        if (registeredUser == null) {
            //if null, this means the register was unsuccessful, show an error message dispalyed in html
            return "redirect:/sign-up";
        }
        return "redirect:/log-in";
    }

    @PostMapping("/log-in")
    public String logIn(@ModelAttribute User user, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println(user);
<<<<<<< Updated upstream

        User storedUser = userService.logInUser(user.getUsername(), user.getPassword());
        boolean passwordMatches = passwordEncoder.matches(user.getPassword(), storedUser.getPassword());
        if (storedUser != null && passwordMatches) {
=======
        headerAccessor.getSessionAttributes().put("userId", user.getUserId());
        headerAccessor.getSessionAttributes().put("username", user.getUsername());
        User loggedInUser = userService.logInUser(user.getUsername(), user.getPassword());
        if (loggedInUser == null) {
>>>>>>> Stashed changes
            //if null, this means unsuccessful, display error message from html
            //located in HomeController
            return "redirect:/home";
        }
        //if null, this means unsuccessful, display error message from html

        return "redirect:/log-in";
    }

    @PostMapping("/updatePapers")
    public String addPaper(@RequestParam Integer userId, @RequestParam Collection<String> paperCodes) {

        // Check if user ID and paper codes are not null and if there are papers to add
        if (userId != null && paperCodes != null) {
            // Convert paper codes to Paper objects
            List<Paper> papers = new ArrayList<>();
            for (String paperCode : paperCodes) {
                Paper paper = new Paper();
                paper.setPaperCode(paperCode);
                papers.add(paper);
            }
            // Call the PaperService to add papers for the user
            User updatedUser = paperService.addUserPapers(userId, papers);
            if (updatedUser == null) {
                // If the operation fails, redirect to an error page or handle accordingly
                return "redirect:/error";
            }
        } else {
            // If no papers are provided, redirect to an error page or display a message
            return "redirect:/error?message=No papers provided";
        }
        // Redirect to the user's profile page or any other page as needed
        return "redirect:/updatePapers"; // Assuming there's a profile page to redirect to
    }

    

}

//    @PostMapping("/addPaper")
//    public String addPaper(@RequestParam Integer userId,  @RequestParam Collection<String> papers) {
//
//// Check if user ID and paper code are not null
//                if (userId != null && papers != null) {
//            // Call the PaperService to add paper for the user
//        User updatedUser = paperService.addUserPapers(userId, papers);
//            if (updatedUser == null) {
//                // If the operation fails, redirect to an error page or handle accordingly
//                return "redirect:/error";
//            }
//        }
//        // Redirect to the user's profile page or any other page as needed
//        return "redirect:/addPaper";
//    }
//    
//    
//    
//
//}
