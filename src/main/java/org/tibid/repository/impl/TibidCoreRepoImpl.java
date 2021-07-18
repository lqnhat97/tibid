package org.tibid.repository.impl;

import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Component;

@Component
public class TibidCoreRepoImpl<T> {
	private Querydsl querydsl;

	@PersistenceContext
	EntityManager entityManager;

	protected Querydsl getQuerydsl() {
		if (querydsl == null) {
			Class<T> classType = ((Class) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0]);
			PathBuilder<T> builder = new PathBuilderFactory().create(classType);
			querydsl = new Querydsl(getEntityManager(), builder);
		}
		return querydsl;
	}

	private boolean ignoreDBCheck = true;

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void flush() {
		getEntityManager().flush();
	}

	public void clear() {
		getEntityManager().clear();
	}
}
