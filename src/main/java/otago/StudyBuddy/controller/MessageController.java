// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */
// package otago.StudyBuddy.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// /**
//  *
//  * @author willi
//  */
// @Controller
// public class MessageController {
// //    
// //    @Autowired
// //    MessageService messageService;
// //
// //    public MessageController(MessageService messageService) {
// //        this.messageService = messageService;
// //        
// //    }
    
    
// }

//
//package otago.StudyBuddy.controller;
//
//import java.util.List;
//
//// Remove the package declaration since it is already declared above
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import otago.StudyBuddy.service.MessageService;
//
//@Controller
//public class MessageController {
//    
//    @Autowired
//    private MessageService messageService;
//
//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("messages", messageService.getMessages());
//        return "chat";  // Assumes you have a Thymeleaf template named 'chat.html'
//    }
//
//    @ResponseBody
//    @GetMapping("/messages")
//    public ResponseEntity<List<String>> getMessages() {
//        return ResponseEntity.ok(messageService.getMessages());
//    }
//
//    @ResponseBody
//    @PostMapping("/messages")
//    public ResponseEntity<?> addMessage(@RequestBody String message) {
//        if (message == null || message.trim().isEmpty()) {
//            return ResponseEntity.badRequest().body("Message cannot be empty");
//        }
//        try {
//            messageService.addMessage(message);
//            return ResponseEntity.ok("Message added successfully");
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("An error occurred while saving the message");
//        }
//    }
//}
