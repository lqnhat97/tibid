package org.tibid.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tibid.dto.BidOrderDto;
import org.tibid.dto.BidTicketDto;
import org.tibid.entity.tiki.Order;
import org.tibid.entity.tiki.request.TikiOrderRequest;
import org.tibid.filter.BaseSearchCriteria;
import org.tibid.filter.OrdersSearchCriteria;
import org.tibid.entity.tiki.ipn.request.IpnRequest;
import org.tibid.kafka.MessageProducer;
import org.tibid.service.TibidService;
import org.tibid.service.tiki.TikiIntegrateService;

import java.util.List;

@RestController
public class TibidController {

	private final MessageProducer producer;

	private final TibidService tibidService;

	private final TikiIntegrateService tikiIntegrateService;

	public TibidController(MessageProducer producer, TibidService tibidService, TikiIntegrateService tikiIntegrateService) {
		this.producer = producer;
		this.tibidService = tibidService;
		this.tikiIntegrateService = tikiIntegrateService;
	}

	@GetMapping("/")
	public String test() {
		return "hello tibid";
	}

	@PostMapping("/tickets")
	public ResponseEntity<BidTicketDto> createTicket(@RequestBody BidTicketDto bidTicketDto) {
		return new ResponseEntity<>(tibidService.createBidTicket(bidTicketDto), HttpStatus.CREATED);
	}

	@GetMapping("/tickets/{id}")
	public ResponseEntity<BidTicketDto> getTicketById(@PathVariable long id) {
		return new ResponseEntity<>(tibidService.getTicketById(id), HttpStatus.OK);
	}

	@DeleteMapping("/tickets/{id}")
	public void deleteTicketById(@PathVariable long id) {
		tibidService.deleteTicketById(id);
	}

	@PostMapping("/orders")
	public ResponseEntity<BidOrderDto> createOrder(@RequestBody BidOrderDto bidOrderDto) {
		return new ResponseEntity<>(tibidService.createBidOrder(bidOrderDto), HttpStatus.CREATED);

	}

	@PostMapping("/orders/search")
	public ResponseEntity<Page<BidOrderDto>> searchOrders(@RequestBody BaseSearchCriteria<OrdersSearchCriteria> searchCriteria) {
		return new ResponseEntity<>(tibidService.searchBidOrder(searchCriteria), HttpStatus.OK);
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<BidOrderDto> getOrderById(@PathVariable long id) {
		return new ResponseEntity<>(tibidService.getOrderById(id), HttpStatus.OK);
	}

	@DeleteMapping("/orders/{id}")
	public void deleteOrderById(@PathVariable long id) {
		tibidService.deleteOrderById(id);
	}

	@GetMapping("/kafka1")
	public String kafkaTest1() {
		producer.sendMessageBidOrderStatus("test message");
		return "Sent!";
	}

	@GetMapping("/kafka2")
	public String kafkaTest2() {
		producer.sendMessageBidTicketStatus("test message");
		return "Sent!";
	}

	@PostMapping("/payment/ipn")
	public ResponseEntity paymentIpn(@RequestBody IpnRequest ipnRequest){
		tibidService.updateBidOrderIpn(ipnRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/payment")
	public ResponseEntity<Order> payment(@RequestHeader(value = "auth-code") String authCode,
										 @RequestBody TikiOrderRequest tikiOrderRequest){
		List<BidTicketDto> bidTicketDtoList = tikiOrderRequest.getData();
	    String customerId = tikiIntegrateService.getAuthToken(authCode);
		Order tikiOrder = tikiIntegrateService.createOrder(bidTicketDtoList, customerId);
		tibidService.updateBidOrder(tikiOrder, bidTicketDtoList);
		return new ResponseEntity<>(tikiOrder, HttpStatus.OK);
	}
}
