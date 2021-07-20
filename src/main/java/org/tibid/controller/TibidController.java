package org.tibid.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tibid.dto.BidOrderDto;
import org.tibid.dto.BidTicketDto;
import org.tibid.filter.BaseSearchCriteria;
import org.tibid.filter.OrdersSearchCriteria;
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
		tibidService.updateBidOrder(ipnRequest);
		return new ResponseEntity(HttpStatus.OK);
	}
}
