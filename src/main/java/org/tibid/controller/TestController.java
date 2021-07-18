package org.tibid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tibid.kafka.MessageProducer;

@RestController
public class TestController {

	@Autowired
	private MessageProducer producer;

	@GetMapping("/")
	public String test(){
		return "hello tibid";
	}

	@GetMapping("/kafka1")
	public String kafkaTest1(){
		producer.sendMessageBidOrderStatus("test message");
		return "Sent!";
	}

	@GetMapping("/kafka2")
	public String kafkaTest2(){
		producer.sendMessageBidTicketStatus("test message");
		return "Sent!";
	}
}
