package com.saleshistory.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

import com.saleshistory.dto.MostSoldProductDto;
import com.saleshistory.dto.QuantityByCategoryDto;
import com.saleshistory.dto.QuantityByCategoryForYearDto;
import com.saleshistory.dto.QuantityBySoldCategoryForYearDto;
import com.saleshistory.dto.QuantitySoldByCategoryDto;
import com.saleshistory.dto.SalesDto;

public interface SalesService {

	public List<SalesDto> getAllSales();

	public List<MostSoldProductDto> findMostSoldProducts();

	public List<SalesDto> getSalesByQuarter(String quarter);

	public List<SalesDto> findByDate(LocalDate date);

	public List<QuantityByCategoryDto> countTotalQuantitiesByCategory();

	public List<QuantityByCategoryForYearDto> countTotalQuantitiesByCategoryForYear(Integer year);

	public List<QuantitySoldByCategoryDto> countTotalQuantitiesSoldByCategory();

	public List<QuantityBySoldCategoryForYearDto> TotalAmountSoldByCategoryForYear(Integer year);
}
