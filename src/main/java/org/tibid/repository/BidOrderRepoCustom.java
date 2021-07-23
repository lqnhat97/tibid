package org.tibid.repository;

import org.springframework.data.domain.Page;
import org.tibid.entity.BidOrderEntity;
import org.tibid.filter.BaseSearchCriteria;
import org.tibid.filter.OrdersSearchCriteria;

public interface BidOrderRepoCustom {
	Page<BidOrderEntity> search(BaseSearchCriteria<OrdersSearchCriteria> searchCriteria);

	int updateOrderStatus();
}
