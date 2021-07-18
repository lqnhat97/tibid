package org.tibid.service;

import java.util.List;

import org.tibid.dto.BidOrderDto;
import org.tibid.dto.BidTicketDto;

public interface TibidService {
	BidTicketDto createBidTicket(BidTicketDto bidTicketDto);
	BidOrderDto createBidOrder(BidOrderDto bidOrderDto);
	List<BidOrderDto> searchBidOrder();
}
