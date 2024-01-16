package com.saleshistory.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChannelsDto {

	
	private Integer channelId;

	private String channelDesc;

	private Character channelClass;

	@NotNull(message="channelClassId can not be null ")
	private Integer channelClassId;

	private String channelTotal;

	@NotNull(message="channelTotalId can not be null ")
	private Integer channelTotalId;
}
