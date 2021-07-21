package org.tibid.dto;

import lombok.Data;

@Data
public class BidOrderDto extends BaseDto {
	private long id;

	private int userId;

	private int productId;

	private String productName;

	private float startPrice;

	private float priceStep;

	private int status;

	private long bidStartTime;

	private long bidEndTime;

	private int bidQuantity;

	private long createdAt;

	private long modifiedAt;

	private float ceilingPrice;

	private String tikiInfo;

	private String tikiOrderInfo;

	private String tikiOrderId;
}
