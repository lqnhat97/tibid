package org.tibid.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BidInfoDto {
	private long userId;

	private float price;

	private String userName;

	private String userAvatar;
}
