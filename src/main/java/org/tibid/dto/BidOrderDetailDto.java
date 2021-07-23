package org.tibid.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BidOrderDetailDto{
	private List<BidTicketDto> bidHistory;
	private BidOrderDto bidOrder;
}
