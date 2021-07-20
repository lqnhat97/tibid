package org.tibid.service;

import org.springframework.data.domain.Page;
import org.tibid.dto.BidOrderDto;
import org.tibid.dto.BidTicketDto;
import org.tibid.filter.BaseSearchCriteria;
import org.tibid.filter.OrdersSearchCriteria;

public interface TibidService {
	BidTicketDto createBidTicket(BidTicketDto bidTicketDto);

	BidOrderDto createBidOrder(BidOrderDto bidOrderDto);

	Page<BidOrderDto> searchBidOrder(BaseSearchCriteria<OrdersSearchCriteria> searchCriteria);

	BidOrderDto getOrderById(long id);

	BidTicketDto getTicketById(long id);

	void deleteOrderById(long id);

	void deleteTicketById(long id);
}
