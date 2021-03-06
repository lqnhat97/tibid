package org.tibid.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bid_order")
public class BidOrderEntity extends BaseEntity {
	@Column(name = "user_id")
	private long userId;

	@Column(name = "product_id")
	private long productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "start_price")
	private float startPrice;

	@Column(name = "price_step")
	private float priceStep;

	@Column(name = "ceiling_price")
	private float ceilingPrice;

	@Column(name = "status")
	private int status;

	@Column(name = "bid_start_time")
	private long bidStartTime;

	@Column(name = "bid_end_time")
	private long bidEndTime;

	@Column(name = "bid_quantity")
	private int bidQuantity;

	@Column(name = "tiki_info", columnDefinition = "TEXT")
	private String tikiInfo;

	@Column(name = "tiki_order_info", columnDefinition = "TEXT")
	private String tikiOrderInfo;

	@Column(name = "tiki_order_id")
	private String tikiOrderId;

	@Column(name = "last_ticket_id")
	private long lastTicketId;
}
