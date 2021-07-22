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
public class BidOrderDto extends BaseDto {

	private long userId;

	private long productId;

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

	private long lastTicketId;
}
