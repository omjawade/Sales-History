package com.saleshistory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.saleshistory.dto.CustomerByCountryDto;
import com.saleshistory.dto.RegionCustomerCountDTO;
import com.saleshistory.entity.Countries;

public interface CountriesRepository extends JpaRepository<Countries, Integer> {

	@Query("SELECT c.countryId.countryName AS countryName, COUNT(c) AS customerCount FROM Customers c GROUP BY c.countryId.countryName")
	List<CustomerByCountryDto> countCustomersByCountry();

	@Query("SELECT c.countryRegion AS region, COUNT(c) AS customerCount FROM Countries c WHERE c.countryRegion= :region"
			+ " GROUP BY c.countryRegion")
	List<RegionCustomerCountDTO> countCustomersByRegion(@Param("region") String region);

}
