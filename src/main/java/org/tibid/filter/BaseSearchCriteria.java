package org.tibid.filter;

import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseSearchCriteria<T> {
	protected static final int DEFAULT_ITEM_PER_PAGE = 20;

	protected static final String DEFAULT_SORT_BY = "id";

	T searchCriteria;

	protected Integer pageNum;

	protected Integer itemPerPage;

	protected String direction;

	protected String sortBy;

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum <= 0 ? 0 : pageNum;
	}

	public void setItemPerPage(Integer itemPerPage) {
		this.itemPerPage = itemPerPage <= 0 ? 0 : itemPerPage;
	}

	public void setDirection(String direction) {
		if (direction == null || direction.trim().isEmpty()) {
			this.direction = Sort.Direction.DESC.toString();
		} else {
			this.direction = direction;
		}
	}

	public void setSortBy(String sortBy) {
		if (sortBy == null || sortBy.trim().isEmpty()) {
			this.sortBy = DEFAULT_SORT_BY;
		} else {
			this.sortBy = sortBy;
		}
	}

	public BaseSearchCriteria() {
		this.pageNum = 0;
		this.itemPerPage = DEFAULT_ITEM_PER_PAGE;
		this.sortBy = DEFAULT_SORT_BY;
		this.direction = Sort.Direction.DESC.toString();
	}
}
