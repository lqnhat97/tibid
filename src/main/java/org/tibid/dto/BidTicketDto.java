package org.tibid.dto;

import lombok.Data;

@Data
public class BidTicketDto extends BaseDto {
	private long id;

	private long userId;

	private long bidOrderId;

	private float price;

	private int status;

	private long createdAt;

	private long modifiedAt;
}
