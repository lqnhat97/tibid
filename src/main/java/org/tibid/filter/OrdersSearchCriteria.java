package org.tibid.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersSearchCriteria {
	private String productName;
	private int orderStatus;
	private long sellerId;
}
