package com.saleshistory.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saleshistory.dto.MostSoldProductDto;
import com.saleshistory.dto.QuantityByCategoryDto;
import com.saleshistory.dto.QuantityByCategoryForYearDto;
import com.saleshistory.dto.QuantityBySoldCategoryForYearDto;
import com.saleshistory.dto.QuantitySoldByCategoryDto;
import com.saleshistory.entity.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {

	@Query("SELECT s FROM Sales s WHERE s.timeId.calendarQuarterDesc = :quarter")
	List<Sales> getSalesByQuarter(@Param("quarter") String quarter);

	@Query(value = "select s.prod_id as prodId,p.prod_name as prodName,"
			+ "p.prod_desc as prodDesc,p.prod_category as prodCategory,"
			+ "p.prod_list_price as prodListPrice ,count(*) as salesCount from "
			+ "sales_history.sales s,sales_history.products p where s.prod_id = p.prod_id "
			+ "group by prodId order by salesCount DESC;", nativeQuery = true)
	List<MostSoldProductDto> findMostSoldProducts();

	List<Sales> findByTimeId_timeId(LocalDate date);

	@Query("select p.prodCategory as prodCategory, count(s.prodId) as salesCount from Products p, Sales s "
			+ "where p.prodId = s.prodId.prodId group by prodCategory")
	List<QuantityByCategoryDto> countTotalQuantitiesByCategory();

	@Query("SELECT p.prodCategory AS prodCategory, t.calendarYear AS calendarYear, COUNT(s) AS salesCount "
			+ "FROM Products p " + "JOIN Sales s ON p.prodId = s.prodId.prodId "
			+ "JOIN Times t ON t.timeId = s.timeId.timeId " + "WHERE t.calendarYear = :year " + "GROUP BY prodCategory")
	List<QuantityByCategoryForYearDto> countTotalQuantitiesByCategoryForYear(@Param("year") Integer year);

	@Query("select p.prodCategory as prodCategory, sum(s.amountSold) as amountSold from Products p, Sales s "
			+ "where p.prodId = s.prodId.prodId group by prodCategory")
	List<QuantitySoldByCategoryDto> countTotalQuantitiesSoldByCategory();

	@Query("SELECT p.prodCategory AS prodCategory, t.calendarYear AS calendarYear, sum(s.amountSold) AS amountSold "
			+ "FROM Products p " + "JOIN Sales s ON p.prodId = s.prodId.prodId "
			+ "JOIN Times t ON t.timeId = s.timeId.timeId " + "WHERE t.calendarYear = :year " + "GROUP BY prodCategory")
	List<QuantityBySoldCategoryForYearDto> TotalAmountSoldByCategoryForYear(@Param("year") Integer year);
}
