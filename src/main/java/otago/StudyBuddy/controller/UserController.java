/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.controller;

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
}
