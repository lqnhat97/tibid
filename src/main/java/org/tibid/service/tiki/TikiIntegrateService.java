package org.tibid.service.tiki;

import org.tibid.dto.BidTicketDto;
import org.tibid.entity.tiki.Order;

import java.util.List;

public interface TikiIntegrateService {
    Order createOrder(List<BidTicketDto> bidTicketDtoList, String customerId);

    String getAuthToken(String authCode);

    Order completeOrder(String orderId);

    Order queryTikiOrder(long orderId);
}
