package com.saleshistory.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saleshistory.dto.ProductsDto;
import com.saleshistory.entity.Products;
import com.saleshistory.exceptions.ChannelsException;
import com.saleshistory.repository.ChannelsRepository;
import com.saleshistory.service.ChannelsService;

@Service
public class ChannelsServiceImpl implements ChannelsService {

	@Autowired
	private ChannelsRepository channelsRepository;

	@Override
	public Double getRevenueByChannel(Integer channelId) {
		if (channelsRepository.calculateRevenueByChannel(channelId) != 0) {
			return channelsRepository.calculateRevenueByChannel(channelId);
		}
		throw new ChannelsException("Channel does not have any Sales.");
	}

	@Override
	public Integer getAggregateSaleByChannel(Integer channelId) {
		if (channelsRepository.calculateAggregateSaleByChannel(channelId) != 0) {
			return channelsRepository.calculateAggregateSaleByChannel(channelId);
		}
		throw new ChannelsException("Channel does not have any Sales.");
	}

	@Override
	public List<ProductsDto> getMostSoldProductsByChannel(Integer channelId) {
		List<Products> productsList = channelsRepository.findMostSoldProductsByChannel(channelId);
		if (!productsList.isEmpty()) {
			List<ProductsDto> productsDtoList = new ArrayList<>();
			for (Products product : productsList) {
				ProductsDto dto = new ProductsDto();
				dto.setProdId(product.getProdId());
				dto.setProdName(product.getProdName());
				dto.setProdDesc(product.getProdDesc());
				dto.setProdSubcategory(product.getProdSubcategory());
				dto.setProdCategory(product.getProdCategory());
				dto.setProdCategoryDesc(product.getProdCategoryDesc());
				dto.setProdEffFrom(product.getProdEffFrom());
				dto.setProdEffTo(product.getProdEffTo());
				dto.setProdListPrice(product.getProdListPrice());
				dto.setProdMinPrice(product.getProdMinPrice());
				dto.setProdPackSize(product.getProdPackSize());
				dto.setProdValid(product.getProdValid());
				dto.setProdWeightClass(product.getProdWeightClass());
				dto.setProdSubcategoryId(product.getProdSubcategoryId());
				dto.setProdSubcategoryDesc(product.getProdSubcategoryDesc());
				dto.setProdStatus(product.getProdStatus());
				dto.setProdUnitOfMeasure(product.getProdUnitOfMeasure());
				dto.setSupplierId(product.getSupplierId().getId());
				dto.setProdTotal(product.getProdTotal());
				dto.setProdTotalId(product.getProdTotalId());
				dto.setProdSrcId(product.getProdSrcId());
				productsDtoList.add(dto);
			}
			return productsDtoList;
		}
		throw new ChannelsException("Products not found for channel Id " + channelId);
	}
}
