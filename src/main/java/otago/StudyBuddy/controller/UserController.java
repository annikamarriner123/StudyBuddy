/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import otago.StudyBuddy.domain.User;
import otago.StudyBuddy.repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, PaperService paperService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userService = userService;
        this.paperService = paperService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
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
    public String getHomePage(Model model) {
        return "home";
    }

    @GetMapping("/update-details")
    public String getUpdateUser(Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("updateUserDetailsRequest", new User());
        return "aboutStudent";
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
    public String logIn(@ModelAttribute User user) {
        System.out.println(user);

        User storedUser = userService.logInUser(user.getUsername(), user.getPassword());
        boolean passwordMatches = passwordEncoder.matches(user.getPassword(), storedUser.getPassword());
        if (storedUser != null && passwordMatches) {
            User loggedInUser = userService.logInUser(user.getUsername(), user.getPassword());
            if (loggedInUser == null) {
                //if null, this means unsuccessful, display error message from html
                //located in HomeController
                return "redirect:/home";
            }
            //if null, this means unsuccessful, display error message from html

            return "redirect:/log-in";

        }
        return "redirect:/log-in";
    }

    @GetMapping("/updatePapers")
    public String getUpdatePapers(Model model) {
        model.addAttribute("updatePapersRequest", new String());
        model.addAttribute("updatePapersRequest", new ArrayList<String>());
        
        User currentUser = userService.getCurrentUser();
        List<String> userPapers = currentUser.getUserPapers();
        model.addAttribute("userPapers", userPapers);
        
        return "updatePapers";
    }

    @PostMapping("/updatePapers")
    public String addPaper(@RequestParam("paperCodes") List<String> papers) {
        User currentUser = userService.getCurrentUser();

        Integer userId = currentUser.getUserId();
        // Check if user ID and paper codes are not null and if there are papers to add
        if (userId != null && papers != null) {

            // Call the PaperService to add papers for the user
            User updatedUser = paperService.addUserPapers(userId, papers.get(0)); //this just adds to the temporary paper variable to database
            updatedUser = paperService.addUserPapers(userId, papers);
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

    @GetMapping("/findStudyPeers")
    public String getFindStudyPeersPage() {
        return "findStudyPeers";
    }

    @PostMapping("/findStudyPeers")
    public String searchUsersByPaper(@RequestParam("paper") String paper, Model model) {
        // Query the database for users with the specified paper attribute
        Collection<User> users = userService.getUsersByPaper(paper);
        model.addAttribute("users", users);

        //Optional<User> users = userRepository.findByPapers(paper);
        // Return the list of users as a response
        return "findStudyPeers";
    }

    @GetMapping("/api/user/details")
    public ResponseEntity<User> getUserDetails() {
        User user = userService.getCurrentUser();
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return null;
    }

}
