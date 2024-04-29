/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public UserController(UserService userService, PaperService paperService) {
        this.userService = userService;
        this.paperService = paperService;
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
    
    @PostMapping("/addPaper")
    public String addPaper(@RequestParam Integer userId,  @RequestParam Collection<String> papers) {

// Check if user ID and paper code are not null
                if (userId != null && papers != null) {
            // Call the PaperService to add paper for the user
        User updatedUser = paperService.addUserPapers(userId, papers);
            if (updatedUser == null) {
                // If the operation fails, redirect to an error page or handle accordingly
                return "redirect:/error";
            }
        }
        // Redirect to the user's profile page or any other page as needed
        return "redirect:/addPaper";
    }
    
    
    

}
