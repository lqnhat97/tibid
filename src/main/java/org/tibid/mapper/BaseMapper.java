package org.tibid.mapper;

import org.springframework.stereotype.Service;

@Service
public interface BaseMapper<D, E>{
	D toDto(E entity);
	E toEntity (D dto);
}
