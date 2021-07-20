package org.tibid.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tibid.dto.BidOrderDto;
import org.tibid.dto.BidTicketDto;
import org.tibid.entity.tiki.ipn.request.IpnRequest;
import org.tibid.kafka.MessageProducer;
import org.tibid.service.TibidService;

@RestController
public class TibidController {

	private final MessageProducer producer;
	private final TibidService tibidService;

	public TibidController(MessageProducer producer, TibidService tibidService) {
		this.producer = producer;
		this.tibidService = tibidService;
	}

	@GetMapping("/")
	public String test(){
		return "hello tibid";
	}

	@PostMapping("/createTicket")
	public ResponseEntity<BidTicketDto> createTiket(@RequestBody BidTicketDto bidTicketDto){
		return new ResponseEntity<>(tibidService.createBidTicket(bidTicketDto), HttpStatus.CREATED);
	}

	@PostMapping("/createOrder")
	public ResponseEntity<BidOrderDto> createOrder(@RequestBody BidOrderDto bidOrderDto){
		return new ResponseEntity<>(tibidService.createBidOrder(bidOrderDto), HttpStatus.CREATED);

	}

	@GetMapping("/searchOrders")
	public ResponseEntity<List<BidOrderDto>> searchOrders(){
		return new ResponseEntity<>(tibidService.searchBidOrder(),HttpStatus.OK);
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

	@PostMapping("/payment/ipn")
	public ResponseEntity paymentIpn(@RequestBody IpnRequest ipnRequest){
		tibidService.updateBidOrder(ipnRequest);
		return new ResponseEntity(HttpStatus.OK);
	}
}
