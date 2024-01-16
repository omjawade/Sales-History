package com.saleshistory.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saleshistory.dto.MostSoldProductDto;
import com.saleshistory.dto.QuantityByCategoryDto;
import com.saleshistory.dto.QuantityByCategoryForYearDto;
import com.saleshistory.dto.QuantityBySoldCategoryForYearDto;
import com.saleshistory.dto.QuantitySoldByCategoryDto;
import com.saleshistory.dto.SalesDto;
import com.saleshistory.service.SalesService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/sales")
public class SalesController {

	@Autowired
	private SalesService saleService;

	/**
     * Get all sales.
     *
     * @return ResponseEntity with a list of sales DTOs
     */
	@GetMapping
	public ResponseEntity<List<SalesDto>> getAllSales() {
		List<SalesDto> allSales =saleService.getAllSales();
		return new ResponseEntity<List<SalesDto>>(allSales, HttpStatus.OK);
	}
	
	 /**
     * Get the most sold products.
     *
     * @return ResponseEntity with a list of most sold product DTOs
     */
	@GetMapping("/most")
	public ResponseEntity<List<MostSoldProductDto>> getMostSoldProducts() {
		List<MostSoldProductDto> mostSoldProductDTOs = saleService.findMostSoldProducts();
        return new ResponseEntity<List<MostSoldProductDto>>(mostSoldProductDTOs, HttpStatus.OK);


	}
	
	/**
     * Get sales by quarter.
     *
     * @param quarter the quarter of the sales
     * @return ResponseEntity with a list of sales DTOs
     */
	@GetMapping("/quarter")
    public ResponseEntity<List<SalesDto>> getSalesByQuarter(@RequestParam("quarter") String quarter) {
        List<SalesDto> salesByQuarter = saleService.getSalesByQuarter(quarter);
        return new ResponseEntity<List<SalesDto>>(salesByQuarter, HttpStatus.OK);

    }
	
	/**
     * Get sales by date.
     *
     * @param date the date of the sales
     * @return ResponseEntity with a list of sales DTOs
     */
	@GetMapping("/date")
    public ResponseEntity<List<SalesDto>> getSalesByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<SalesDto> salesBydate = saleService.findByDate(date);
        return new ResponseEntity<List<SalesDto>>(salesBydate, HttpStatus.OK);
    }
	
	/**
     * Get total quantities sold by category.
     *
     * @return ResponseEntity with a list of quantity sold by category DTOs
     */
	@GetMapping("/qtys/categorywise")
    public ResponseEntity<List<QuantityByCategoryDto>> getTotalQuantitiesByCategory() {
		List<QuantityByCategoryDto> quantitiesByCategory = saleService.countTotalQuantitiesByCategory();
        return ResponseEntity.ok(quantitiesByCategory);
    }
	
	 /**
     * Get total quantities sold by category for a specific year.
     *
     * @param year the year
     * @return ResponseEntity with a list of quantity sold by category for year DTOs
     */
	@GetMapping("/qtys/categorywise/year/{year}")
    public ResponseEntity<List<QuantityByCategoryForYearDto>> getQuantityByCategoryForYearDto(@PathVariable Integer year) {
		List<QuantityByCategoryForYearDto> quantitiesByCategoryForYear = saleService.countTotalQuantitiesByCategoryForYear(year);
        return ResponseEntity.ok(quantitiesByCategoryForYear);
    }
	
	/**
     * Get total quantities sold by category.
     *
     * @return ResponseEntity with a list of quantity sold by category DTOs
     */
	@GetMapping("/sold/categorywise")
    public ResponseEntity<List<QuantitySoldByCategoryDto>> getTotalQuantitiesSoldByCategory() {
		List<QuantitySoldByCategoryDto> quantitiesSoldByCategory = saleService.countTotalQuantitiesSoldByCategory();
        return ResponseEntity.ok(quantitiesSoldByCategory);
    }
	
	/**
     * Get total quantities sold by category for a specific year.
     *
     * @param year the year
     * @return ResponseEntity with a list of quantity sold by category for year DTOs
     */
	@GetMapping("/sold/categorywise/year/{year}")
    public ResponseEntity<List<QuantityBySoldCategoryForYearDto>> getTotalQuantitiesSoldByCategoryForYear(@PathVariable Integer year) {
		List<QuantityBySoldCategoryForYearDto> quantitiesSoldByCategory = saleService.TotalAmountSoldByCategoryForYear(year);
        return ResponseEntity.ok(quantitiesSoldByCategory);
    }
}
