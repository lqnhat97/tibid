package org.tibid.mapper;

import java.util.List;

import org.tibid.dto.BidOrderDto;
import org.tibid.entity.BidOrderEnity;

public interface BidOrderMapper extends BaseMapper<BidOrderDto, BidOrderEnity> {
	List<BidOrderDto> toDtoList(List<BidOrderEnity> enityList);
}
