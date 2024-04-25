/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package otago.StudyBuddy.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import otago.StudyBuddy.domain.Message;

/**
 *
 * @author willi
 */
@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")//used to invoke the send message
    @SendTo("/topic/public")//Where it will be sent
    public Message sendMessage(@Payload Message chatMessage) {
        return chatMessage;
    }
   
    
    
    
}

//A room will be at minium two people there will be a room id which seperates them 
//the chat room will have recipient id/s and sender id sender id will be the logged in user 
//recipient id will be done by mathcing userId? username? first and lastname?
//nned to also incldue notifications focus on email notification based on the users email should be able to be turned off/on
//each message in the chat room will habe its own unique message Id for that chat room 

