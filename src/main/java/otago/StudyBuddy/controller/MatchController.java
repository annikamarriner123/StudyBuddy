/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author glenm
 */
@Controller
public class MatchController {

    @GetMapping("/match")
    public String getMatchingPage() {
        return "match";
    }

//    @GetMapping("/findStudyPeers")
//    public String getFindStudyPeersPage() {
//        return "findStudyPeers";
//    }
    
//    @PostMapping("/findStudyPeers")
//    public String findStudyPeers(@RequestParam String paper) {
//        
//        return null;
//    }

}
