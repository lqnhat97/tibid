package org.tibid.utils;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;

import java.lang.reflect.Field;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.tibid.filter.BaseSearchCriteria;

public class SearchHelper {
	private SearchHelper() {
	}

	public static Pageable getPageableObj(BaseSearchCriteria<?> searchCriteria) {
		Direction direction = searchCriteria.getDirection().equals(Sort.DEFAULT_DIRECTION.toString())
				? Sort.DEFAULT_DIRECTION : Direction.DESC;
		return PageRequest.of(searchCriteria.getPageNum()
				, searchCriteria.getItemPerPage()
				, direction
				, searchCriteria.getSortBy());
	}

	public static OrderSpecifier[] getOrderFromPageAble(@NotNull Pageable pageable, @NotNull Class klass) {

		String className = klass.getSimpleName();
		final String orderVariable = String.valueOf(Character.toLowerCase(className.charAt(0)))
				.concat(className.substring(1));

		return pageable.getSort().stream()
				.map(order -> new OrderSpecifier(
						Order.valueOf(order.getDirection().toString()),
						new PathBuilder(klass, orderVariable).get(order.getProperty()))
				)
				.toArray(OrderSpecifier[]::new);
	}

	public static void validateSortField(String sortField, Class<?> clazz) {
		Field field = null;
		while (field == null) {
			try {
				field = clazz.getDeclaredField(sortField);
			} catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass();
				if (clazz == null) {
					throw new RuntimeException("Not found: " + sortField);
				}
			}
		}

	}
}
