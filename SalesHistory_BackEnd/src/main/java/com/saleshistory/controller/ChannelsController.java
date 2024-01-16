package com.saleshistory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saleshistory.dto.ProductsDto;
import com.saleshistory.service.ChannelsService;

@RestController
@RequestMapping("/channels")
public class ChannelsController {

	@Autowired
	private ChannelsService channelsService;

	
	/**
	 * Retrieve the revenue of a channel based on the channel ID.
	 *
	 * @param channelId the ID of the channel
	 * @return ResponseEntity containing the revenue as a Double
	 */
	@GetMapping("/{channelId}/revenue")
	public ResponseEntity<Double> getRevenueByChannelId(@PathVariable Integer channelId) {
		Double revenueOfChannelService = channelsService.getRevenueByChannel(channelId);
		return new ResponseEntity<Double>(revenueOfChannelService, HttpStatus.OK);
	}

	
	/**
	 * Retrieve the aggregate sale of a channel based on the channel ID.
	 *
	 * @param channelId the ID of the channel
	 * @return ResponseEntity containing the aggregate sale as an Integer
	 */
	@GetMapping("/{channelId}/aggregatesale")
	public ResponseEntity<Integer> getAggregateSaleByChannel(@PathVariable Integer channelId) {
		Integer aggregatOfTheSale = channelsService.getAggregateSaleByChannel(channelId);
		return new ResponseEntity<Integer>(aggregatOfTheSale, HttpStatus.OK);
	}
	
	
	/**
	 * Retrieve the most sold products of a channel based on the channel ID.
	 *
	 * @param channelId the ID of the channel
	 * @return ResponseEntity containing a list of ProductsDto
	 */
	@GetMapping("/{channelId}/mostSoldProducts")
	public ResponseEntity<List<ProductsDto>> getMostSoldProductsByChannel(@PathVariable Integer channelId) {
		List<ProductsDto> productsDtoList = channelsService.getMostSoldProductsByChannel(channelId);
		return new ResponseEntity<List<ProductsDto>>(productsDtoList, HttpStatus.OK);
	}
}
