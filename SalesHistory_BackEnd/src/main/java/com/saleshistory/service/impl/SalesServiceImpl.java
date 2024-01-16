package com.saleshistory.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saleshistory.dto.MostSoldProductDto;
import com.saleshistory.dto.QuantityByCategoryDto;
import com.saleshistory.dto.QuantityByCategoryForYearDto;
import com.saleshistory.dto.QuantityBySoldCategoryForYearDto;
import com.saleshistory.dto.QuantitySoldByCategoryDto;
import com.saleshistory.dto.SalesDto;
import com.saleshistory.entity.Sales;
import com.saleshistory.exceptions.SalesException;
import com.saleshistory.repository.SalesRepository;
import com.saleshistory.service.SalesService;

@Service
public class SalesServiceImpl implements SalesService {

	@Autowired
	private SalesRepository salesRepo;

	@Override
	public List<SalesDto> getAllSales() {
		List<Sales> salesList = salesRepo.findAll();
		if (!salesList.isEmpty()) {
			List<SalesDto> saleDtoList = new ArrayList<>();
			for (Sales sale : salesList) {
				SalesDto dto = new SalesDto();
				dto.setTimeId(sale.getTimeId().getTimeId());
				dto.setChannelId(sale.getChannelId().getChannelId());
				dto.setPromoId(sale.getPromoId().getPromoId());
				dto.setCustId(sale.getCustId().getCustId());
				dto.setProdId(sale.getProdId().getProdId());

				BeanUtils.copyProperties(sale, dto);
				saleDtoList.add(dto);
			}
			return saleDtoList;
		}
		throw new SalesException("Sales List is Empty");
	}

	@Override
	public List<MostSoldProductDto> findMostSoldProducts() {
		return salesRepo.findMostSoldProducts();
	}

	@Override
	public List<SalesDto> getSalesByQuarter(String quarter) {
		List<Sales> salesList = salesRepo.getSalesByQuarter(quarter);
		if (!salesList.isEmpty()) {
			List<SalesDto> saleDtoList = new ArrayList<>();
			for (Sales sale : salesList) {
				SalesDto dto = new SalesDto();
				dto.setProdId(sale.getProdId().getProdId());
				dto.setCustId(sale.getCustId().getCustId());
				dto.setPromoId(sale.getPromoId().getPromoId());
				dto.setTimeId(sale.getTimeId().getTimeId());
				dto.setChannelId(sale.getChannelId().getChannelId());
				BeanUtils.copyProperties(sale, dto);
				saleDtoList.add(dto);
			}
			return saleDtoList;
		}
		throw new SalesException("Sales Not found for quarter " + quarter);
	}

	@Override
	public List<SalesDto> findByDate(LocalDate date) {
		List<Sales> salesList = salesRepo.findByTimeId_timeId(date);
		if (!salesList.isEmpty()) {
			List<SalesDto> saleDtoList = new ArrayList<>();
			for (Sales sale : salesList) {
				SalesDto dto = new SalesDto();
				dto.setProdId(sale.getProdId().getProdId());
				dto.setCustId(sale.getCustId().getCustId());
				dto.setPromoId(sale.getPromoId().getPromoId());
				dto.setTimeId(sale.getTimeId().getTimeId());
				dto.setChannelId(sale.getChannelId().getChannelId());
				BeanUtils.copyProperties(sale, dto);
				saleDtoList.add(dto);
			}
			return saleDtoList;
		}
		throw new SalesException("Sales not found for date " + date);
	}

	@Override
	public List<QuantityByCategoryDto> countTotalQuantitiesByCategory() {
		List<QuantityByCategoryDto> quantityByCategoryDtos = salesRepo.countTotalQuantitiesByCategory();
		if (!quantityByCategoryDtos.isEmpty()) {
			return salesRepo.countTotalQuantitiesByCategory();
		}
		throw new SalesException("No Count is Present for Category");
	}

	@Override
	public List<QuantityByCategoryForYearDto> countTotalQuantitiesByCategoryForYear(Integer year) {
		List<QuantityByCategoryForYearDto> quantityByCategoryForYearDtos = salesRepo
				.countTotalQuantitiesByCategoryForYear(year);
		if (!quantityByCategoryForYearDtos.isEmpty()) {
			return salesRepo.countTotalQuantitiesByCategoryForYear(year);
		}
		throw new SalesException("No Category Count is Present for year " + year);
	}

	@Override
	public List<QuantitySoldByCategoryDto> countTotalQuantitiesSoldByCategory() {
		List<QuantitySoldByCategoryDto> quantitySoldByCategoryDtos = salesRepo.countTotalQuantitiesSoldByCategory();
		if (!quantitySoldByCategoryDtos.isEmpty()) {
			return salesRepo.countTotalQuantitiesSoldByCategory();
		}
		throw new SalesException("No Count is Present for Quantity");
	}

	@Override
	public List<QuantityBySoldCategoryForYearDto> TotalAmountSoldByCategoryForYear(Integer year) {
		List<QuantityBySoldCategoryForYearDto> quantityBySoldCategoryForYearDtos = salesRepo
				.TotalAmountSoldByCategoryForYear(year);
		if (!quantityBySoldCategoryForYearDtos.isEmpty()) {
			return salesRepo.TotalAmountSoldByCategoryForYear(year);
		}
		throw new SalesException("No quantity amount Present for year" + year);
	}

}
