package org.tibid.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.tibid.entity.BidOrderEnity;
import org.tibid.entity.QBidOrderEnity;
import org.tibid.entity.QBidTicketEntity;
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

	@Override
	public int updateOrderStatus() {
		ZoneId zoneId = ZoneId.systemDefault();
		long timeNow = LocalDateTime.now().atZone(zoneId).toEpochSecond();
		Query query = em.createNativeQuery("update bid_order set status = 2 where status = 1 and :timeNow between bid_start_time and bid_end_time");
		query.setParameter("timeNow", timeNow);
		int updatedRows = query.executeUpdate();

		this.em.flush();

		query = em.createNativeQuery("update bid_order set status = 3 where status = 2 and :timeNow >= bid_end_time");
		query.setParameter("timeNow", timeNow);
		updatedRows += query.executeUpdate();

		return updatedRows;
	}
}
