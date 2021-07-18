package org.tibid.dto;

import lombok.Data;

@Data
public class BidOrderDto extends BaseDto {
	private int userId;

	private int productId;

	private float startPrice;

	private float priceStep;

	private int status;

	private long bidStartTime;

	private long bidEndTime;

	private String type;

	private int bidQuantity;
}
