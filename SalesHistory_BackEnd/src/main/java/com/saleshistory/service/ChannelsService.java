package com.saleshistory.service;

import java.util.List;

import com.saleshistory.dto.ProductsDto;


public interface ChannelsService {

	public Double getRevenueByChannel(Integer channelId);

	public Integer getAggregateSaleByChannel(Integer channelId);

	public List<ProductsDto> getMostSoldProductsByChannel(Integer channelId);

}
