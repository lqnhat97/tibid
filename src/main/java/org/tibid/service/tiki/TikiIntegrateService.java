package org.tibid.service.tiki;

import org.springframework.stereotype.Service;
import org.tibid.dto.BidOrderDto;

import org.tibid.entity.tiki.Order;

@Service
public interface TikiIntegrateService {
    Order createOrder(BidOrderDto bidOrderDto);
}
