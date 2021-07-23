package org.tibid.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gson.Gson;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tibid.dto.BidInfoDto;
import org.tibid.dto.BidOrderDetailDto;
import org.tibid.dto.BidOrderDto;
import org.tibid.dto.BidTicketLastDetailDto;
import org.tibid.dto.BidTicketDto;
import org.tibid.entity.BidOrderEntity;
import org.tibid.entity.tiki.Order;
import org.tibid.entity.tiki.ipn.request.IpnRequest;
import org.tibid.entity.tiki.request.TikiOrderRequest;
import org.tibid.filter.BaseSearchCriteria;
import org.tibid.filter.OrdersSearchCriteria;
import org.tibid.service.TibidService;
import org.tibid.service.tiki.TikiIntegrateService;

import lombok.AllArgsConstructor;
//import org.tibid.socket.MyStompSessionHandler;

@RestController
@AllArgsConstructor
public class TibidController {

	private final TibidService tibidService;

	private final TikiIntegrateService tikiIntegrateService;

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

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
	public ResponseEntity<BidOrderDetailDto> getOrderById(@PathVariable long id) {
		return new ResponseEntity<>(tibidService.getOrderById(id), HttpStatus.OK);
	}

	@DeleteMapping("/orders/{id}")
	public void deleteOrderById(@PathVariable long id) {
		tibidService.deleteOrderById(id);
	}

	@PostMapping("/payment/ipn")
	public ResponseEntity paymentIpn(@RequestBody IpnRequest ipnRequest) {
		tibidService.updateBidOrderIpn(ipnRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/payment")
	public ResponseEntity payment(@RequestHeader(value = "auth-code") String authCode,
			@RequestBody TikiOrderRequest tikiOrderRequest) {
		List<BidTicketDto> bidTicketDtoList = tikiOrderRequest.getData();
		String customerId = tikiIntegrateService.getAuthToken(authCode);
		if (StringUtils.isNullOrEmpty(customerId)) {
			Map<String, String> data = new HashMap<>();
			data.put("message", "CustomerId not found");
			return new ResponseEntity(data, HttpStatus.BAD_REQUEST);
		}
		Order tikiOrder = tikiIntegrateService.createOrder(bidTicketDtoList, customerId);
		tibidService.updateBidOrderFromTiki(tikiOrder, bidTicketDtoList);
		return new ResponseEntity<>(tikiOrder, HttpStatus.OK);
	}

	@GetMapping("/tiki/order/{orderId}")
	public ResponseEntity queryTikiOrder(@PathVariable long orderId) {
		tikiIntegrateService.queryTikiOrder(orderId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/tickets/search")
	public ResponseEntity<List<BidTicketLastDetailDto>> getUserTicketByStatus(@RequestParam long userId, @RequestParam int status) {
		return new ResponseEntity<>(tibidService.getTicketDetailByUserId(userId, status), HttpStatus.OK);
	}

	@PostMapping("/orders/{id}/bid")
	public void bid(@PathVariable long id, @RequestBody BidInfoDto bidInfoDto) {
		BidOrderEntity bidOrderEntity = tibidService.bid(id, bidInfoDto);
		HashMap<String,Object> payload = new HashMap<>();
		payload.put("bidInfoDto",bidInfoDto);
		payload.put("bidOrderEntity", bidOrderEntity);
		Logger.getLogger(this.getClass().getName()).info("send payload " + new Gson().toJson(payload));
		sendToTopicOrder(bidOrderEntity, payload);
	}

	@PostMapping("/orders/{id}/bidWin")
	public void bidWin(@PathVariable long id, @RequestBody BidInfoDto bidInfoDto) {
		BidOrderEntity bidOrderEntity = tibidService.bidWin(id, bidInfoDto);
		HashMap<String,Object> payload = new HashMap<>();
		payload.put("bidInfoDto",bidInfoDto);
		payload.put("bidOrderEnity", bidOrderEntity);
		sendToTopicOrder(bidOrderEntity, payload);
	}

	private void sendToTopicOrder(BidOrderEntity bidOrderEntity, HashMap<String, Object> payload) {
		messagingTemplate.convertAndSend("/topic/order/" + bidOrderEntity.getId(), payload);
	}
}
