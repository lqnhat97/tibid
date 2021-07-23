package org.tibid.service;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tibid.dto.BidInfoDto;
import org.tibid.dto.BidOrderDto;
import org.tibid.dto.BidTicketDetailDto;
import org.tibid.dto.BidTicketDto;
import org.tibid.entity.BidOrderEnity;
import org.tibid.entity.BidTicketEntity;
import org.tibid.entity.tiki.Order;
import org.tibid.entity.tiki.ipn.request.IpnRequest;
import org.tibid.filter.BaseSearchCriteria;
import org.tibid.filter.OrdersSearchCriteria;
import org.tibid.mapper.BidOrderMapper;
import org.tibid.mapper.BidTicketMapper;
import org.tibid.repository.BidOrderRepo;
import org.tibid.repository.BidTicketRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class TibidServiceImpl implements TibidService {

	private final BidTicketRepo bidTicketRepo;

	private final BidOrderRepo bidOrderRepo;

	private final BidTicketMapper bidTicketMapper;

	private final BidOrderMapper bidOrderMapper;

	private final Gson gson = new Gson();

	@Override
	public BidTicketDto createBidTicket(BidTicketDto bidTicketDto) {
		BidTicketEntity result = bidTicketRepo.save(bidTicketMapper.toEntity(bidTicketDto));
		return bidTicketMapper.toDto(result);
	}

	@Override
	public BidOrderDto createBidOrder(BidOrderDto bidOrderDto) {
		BidOrderEnity result = bidOrderRepo.save(bidOrderMapper.toEntity(bidOrderDto));
		return bidOrderMapper.toDto(result);
	}

	// Temporary find all
	@Override
	public Page<BidOrderDto> searchBidOrder(BaseSearchCriteria<OrdersSearchCriteria> searchCriteria) {
		this.updateAllOrdersStatus();
		Page<BidOrderEnity> pageResult = bidOrderRepo.search(searchCriteria);
		return pageResult.map(entity -> bidOrderMapper.toDto(entity));
	}

	@Override
	public BidOrderDto getOrderById(long id) {
		return Optional.ofNullable(bidOrderMapper.toDto(bidOrderRepo.findById(id).get())).orElse(null);
	}

	@Override
	public BidTicketDto getTicketById(long id) {
		return Optional.ofNullable(bidTicketMapper.toDto(bidTicketRepo.findById(id).get())).orElse(null);
	}

	@Override
	public void deleteOrderById(long id) {
		bidOrderRepo.deleteById(id);
	}

	@Override
	public void deleteTicketById(long id) {
		bidTicketRepo.deleteById(id);
	}

	// Temporary find all
	@Override
	public int updateBidOrderIpn(IpnRequest ipnRequest) {

		BidOrderEnity bidOrderEnity = bidOrderRepo.findByTikiOrderId(ipnRequest.getOrder().getId());
		//Update the id
		bidOrderEnity.setTikiOrderId(gson.toJson(ipnRequest.getOrder()));
		bidOrderRepo.save(bidOrderEnity);

		return 1;
	}

	@Override
	public List<BidOrderEnity> updateBidOrderFromTiki(Order order, List<BidTicketDto> bidTicketDtoList) {
		List<BidOrderEnity> result = new ArrayList<>();
		for (BidTicketDto bidTicketDto : bidTicketDtoList) {
			BidTicketEntity bidTicketEntity = bidTicketMapper.toEntity(bidTicketDto);

			Optional<BidOrderEnity> bidOrderEntityOptional = bidOrderRepo.findById((bidTicketEntity.getBidOrderId()));
			BidOrderEnity bidOrderEnity = new BidOrderEnity();
			if (bidOrderEntityOptional.isPresent()) {
				bidOrderEnity = bidOrderEntityOptional.get();
			}
			bidOrderEnity.setTikiOrderId(order.getId());
			bidOrderEnity.setTikiOrderInfo(gson.toJson(order));
			result.add(bidOrderRepo.save(bidOrderEnity));
		}
		return result;

	}

	private void updateAllOrdersStatus() {
		int updatedRow = bidOrderRepo.updateOrderStatus();
		log.info("Updated: {} rows - fetched", updatedRow);
	}

	@Override
	public List<BidTicketDetailDto> getTicketDetailByUserId(long userId, int status) {
		List<BidTicketDetailDto> result = new ArrayList<>();
		bidOrderRepo.findByUserBidingIdAndStatus(userId, status).forEach(bidOrderEnity -> {
			result.add(BidTicketDetailDto.builder()
					.orderDto(bidOrderMapper.toDto(bidOrderEnity))
					.ticketDto(this.getLastestTicketOfUser(userId, bidOrderEnity.getId(), status))
					.build());
		});
		return result;
	}

	@Override
	public BidOrderEnity bid(long orderId, BidInfoDto bidInfoDto) {
		BidTicketEntity bidTicketEntity = bidTicketRepo.save(bidTicketMapper.toEntity(BidTicketDto.builder()
				.bidOrderId(orderId)
				.price(bidInfoDto.getPrice())
				.userId(bidInfoDto.getUserId())
				.userAvatar(bidInfoDto.getUserAvatar())
				.userName(bidInfoDto.getUserName())
				.status(1)
				.build()));

		BidOrderEnity bidOrderEnity = bidOrderRepo.findById(bidTicketEntity.getBidOrderId()).get();
		bidOrderEnity.setLastTicketId(bidTicketEntity.getId());
		return bidOrderRepo.save(bidOrderEnity);
	}

	@Override
	public BidOrderEnity bidWin(long orderId, BidInfoDto bidInfoDto) {
		int updatedRow = bidTicketRepo.updateTicketStatus(3);
		log.info("Updated: {} rows to fail because this bid has ended", updatedRow);

		BidTicketEntity bidTicketEntity = bidTicketRepo.save(bidTicketMapper.toEntity(BidTicketDto.builder()
				.bidOrderId(orderId)
				.price(bidInfoDto.getPrice())
				.userId(bidInfoDto.getUserId())
				.userAvatar(bidInfoDto.getUserAvatar())
				.userName(bidInfoDto.getUserName())
				.status(2)
				.build()));

		BidOrderEnity bidOrderEnity = bidOrderRepo.findById(bidTicketEntity.getBidOrderId()).get();
		bidOrderEnity.setLastTicketId(bidTicketEntity.getId());
		bidOrderEnity.setStatus(3);
		return bidOrderRepo.save(bidOrderEnity);
	}

	private BidTicketDto getLastestTicketOfUser(long userid, long orderId, int status) {
		return bidTicketMapper.toDto(bidTicketRepo.findLastRecordBy(userid, orderId, status));
	}
}
