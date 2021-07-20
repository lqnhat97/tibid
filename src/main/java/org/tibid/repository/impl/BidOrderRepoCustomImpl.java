package org.tibid.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.tibid.entity.BidOrderEnity;
import org.tibid.entity.QBidOrderEnity;
import org.tibid.filter.BaseSearchCriteria;
import org.tibid.filter.OrdersSearchCriteria;
import org.tibid.repository.BidOrderRepoCustom;
import org.tibid.utils.SearchHelper;

public class BidOrderRepoCustomImpl implements BidOrderRepoCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<BidOrderEnity> search(BaseSearchCriteria<OrdersSearchCriteria> searchCriteria) {
		QBidOrderEnity qBidOrderEnity = QBidOrderEnity.bidOrderEnity;

		BooleanBuilder builder = new BooleanBuilder();
		if (!StringUtils.isEmpty(searchCriteria.getSearchCriteria().getProductName())) {
			builder.and(qBidOrderEnity.productName.contains(searchCriteria.getSearchCriteria().getProductName()));
		}

		Pageable pageable = SearchHelper.getPageableObj(searchCriteria);

		JPAQuery<BidOrderEnity> query = new JPAQuery<>(em);
		query.from(qBidOrderEnity)
				.where(builder)
				.limit(pageable.getPageSize())
				.offset(pageable.getOffset());

		long total = query.fetchCount();
		List<BidOrderEnity> testCases = query.fetch();

		return new PageImpl<>(testCases, pageable, total);
	}
}
