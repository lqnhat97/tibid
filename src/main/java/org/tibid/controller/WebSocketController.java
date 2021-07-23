//package org.tibid.controller;
//
//import com.google.gson.Gson;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.stereotype.Controller;
//import org.tibid.dto.BidOrderDto;
//
//import java.util.HashMap;
//import java.util.logging.Logger;
//
//@Controller
//public class WebSocketController {
//
//    @Autowired
//    private SimpMessageSendingOperations messagingTemplate;
//
//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/publicChatRoom")
//    public BidOrderDto sendMessage(@Payload BidOrderDto bidOrderDto) {
//        return bidOrderDto;
//    }
//
//
//    @MessageMapping("/topic/order/1")
//    public void test(@Payload HashMap<String,Object> payload) {
//        Logger.getLogger(this.getClass().getName()).info("payload " + new Gson().toJson(payload));
//    }
//}
