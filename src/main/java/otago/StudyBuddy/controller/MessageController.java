// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */
 package otago.StudyBuddy.controller;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
import otago.StudyBuddy.service.MessageService;
 /**
  *
  * @author willi
  */
 @Controller
 public class MessageController {
    
    @Autowired
    MessageService messageService;

   public MessageController(MessageService messageService) {
        this.messageService = messageService;     
    }
    
    
 }
