package org.tibid.repository;

import org.springframework.data.domain.Page;
import org.tibid.entity.BidOrderEnity;
import org.tibid.filter.BaseSearchCriteria;
import org.tibid.filter.OrdersSearchCriteria;

public interface BidOrderRepoCustom {
	Page<BidOrderEnity> search(BaseSearchCriteria<OrdersSearchCriteria> searchCriteria);

	int updateOrderStatus();
}
