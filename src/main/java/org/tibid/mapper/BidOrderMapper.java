package org.tibid.mapper;

import java.util.List;

import org.tibid.dto.BidOrderDto;
import org.tibid.entity.BidOrderEntity;

public interface BidOrderMapper extends BaseMapper<BidOrderDto, BidOrderEntity> {
	List<BidOrderDto> toDtoList(List<BidOrderEntity> enityList);

}
