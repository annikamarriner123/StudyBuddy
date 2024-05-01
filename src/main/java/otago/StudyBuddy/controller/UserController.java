/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import otago.StudyBuddy.domain.User;
import otago.StudyBuddy.service.UserService;
/**
 *
 * @author glenm
 */
@Controller
public class UserController {
    
    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
    
    @PostMapping("/sign-up")
    public String register(@ModelAttribute User user) {
        System.out.println(user);
        
        User registeredUser = userService.registerUser(user.getFirstName(), user.getSurname(), user.getUsername(), user.getPassword(), user.getEmail());
        if(registeredUser == null) {
            //if null, this means the register was unsuccessful, show an error message dispalyed in html
            return "redirect:/sign-up";
        }
        return "redirect:/log-in";
    }
    
    @PostMapping("/log-in")
    public String logIn(@ModelAttribute User user) {
        System.out.println(user);
        
        User loggedInUser = userService.logInUser(user.getUsername(), user.getPassword());
        if(loggedInUser == null) {
            //if null, this means unsuccessful, display error message from html
            return "redirect:/log-in";
        }
        //located in HomeController
        return "redirect:/home";
    }

    @GetMapping("/updatePapers")
    public String getUpdatePapers(Model model) {
        model.addAttribute("updatePapersRequest", new String());
        return "updatePapers";
    }

    @PostMapping("/updatePapers")
    public String addPaper(@ModelAttribute Paper paper) {
        User currentUser = userService.getCurrentUser();

        Integer userId = currentUser.getUserId();
        // Check if user ID and paper codes are not null and if there are papers to add
        if (userId != null && paper != null) {

            // Call the PaperService to add papers for the user
            User updatedUser = paperService.addUserPapers(userId, paper.getPaperCode());
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

    @PostMapping("update-details")
    public String updateUserDetails(@ModelAttribute User user) {

        User updatedUser = userService.updateUserDetails(user.getFirstName(), user.getSurname(), user.getMajor(), user.getEmail());
        if (updatedUser == null) {
            return "redirect:/error";
        }
        return "redirect:/update-details";

    }

    @GetMapping("/searchUsers")
    public ResponseEntity<Optional<User>> searchUsersByPaper(@RequestParam String paper) {
        // Query the database for users with the specified paper attribute
        Optional<User> users = userRepository.findByPapers(paper);

        // Return the list of users as a response
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/api/user/details")
    public ResponseEntity<User> getUserDetails() {
        User user = userService.getCurrentUser();// Assuming the user details are stored in the authentication principal
        return ResponseEntity.ok(user);
    }

}
