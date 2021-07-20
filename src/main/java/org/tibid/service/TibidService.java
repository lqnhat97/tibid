package org.tibid.service;

import java.util.List;

import org.tibid.dto.BidOrderDto;
import org.tibid.dto.BidTicketDto;
import org.tibid.entity.tiki.ipn.request.IpnRequest;

public interface TibidService {
	BidTicketDto createBidTicket(BidTicketDto bidTicketDto);
	BidOrderDto createBidOrder(BidOrderDto bidOrderDto);
	List<BidOrderDto> searchBidOrder();

	int updateBidOrder(IpnRequest ipnRequest);
}
