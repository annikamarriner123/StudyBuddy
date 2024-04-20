/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author glenm
 */
@Controller
public class UserController {
    
    @GetMapping("/sign-up")
    public String signUp() {
        return "sign-up";
    }
    
    @GetMapping("/login")
    public String logIn() {
        return "login";
    }
}
