package org.tibid.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.tibid.dto.BidInfoDto;
import org.tibid.dto.BidOrderDto;
import org.tibid.dto.BidTicketDetailDto;
import org.tibid.dto.BidTicketDto;
import org.tibid.entity.BidOrderEnity;
import org.tibid.entity.tiki.Order;
import org.tibid.entity.tiki.ipn.request.IpnRequest;
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

	int updateBidOrderIpn(IpnRequest ipnRequest);

	List<BidOrderEnity> updateBidOrderFromTiki(Order order, List<BidTicketDto> bidOrderDto);

	List<BidTicketDetailDto> getTicketDetailByUserId(long userId, int status);

	void bid(long orderId, BidInfoDto bidInfoDto);
	void bidWin(long orderId, BidInfoDto bidInfoDto);
}
