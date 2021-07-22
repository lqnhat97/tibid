package org.tibid.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BidTicketDto extends BaseDto {

	private long userId;

	private long bidOrderId;

	private float price;

	private int status;

	private long createdAt;

	private long modifiedAt;
}
