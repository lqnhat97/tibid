package org.tibid.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BidTicketLastDetailDto {
	private BidTicketDto ticketDto;

	private BidOrderDto orderDto;
}
