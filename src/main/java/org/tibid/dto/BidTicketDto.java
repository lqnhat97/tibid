package org.tibid.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BidTicketDto extends BaseDto {
	private long id;

	private long userId;

	private long bidOrderId;

	private float price;

	private int status;

	private long createdAt;

	private long modifiedAt;
}
