package org.tibid.dto;

import lombok.Data;

@Data
public class BidTicketDto extends BaseDto {
	private int userId;

	private int bidOrderId;

	private float price;

	private int status;
}
