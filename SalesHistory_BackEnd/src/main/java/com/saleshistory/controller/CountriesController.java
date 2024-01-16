package com.saleshistory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saleshistory.dto.CountriesDto;
import com.saleshistory.dto.CustomerByCountryDto;
import com.saleshistory.dto.RegionCustomerCountDTO;
import com.saleshistory.dto.ResponseDto;
import com.saleshistory.service.CountriesService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/countries")
public class CountriesController {

	@Autowired
	private CountriesService countryService;
	
	/**
     * Save a new country.
     *
     * @param countriesDto the DTO containing country information
     * @return ResponseEntity with the response DTO
     */
	@PostMapping
	public ResponseEntity<ResponseDto> saveCountry(@Valid @RequestBody CountriesDto countriesDto)
	{
		ResponseDto countryDto=countryService.addCountry(countriesDto);
		return new ResponseEntity<ResponseDto>(countryDto,HttpStatus.OK);
	}
	
	/**
     * Update an existing country.
     *
     * @param countriesDto the DTO containing updated country information
     * @return ResponseEntity with the response DTO
     */
	@PutMapping
	public ResponseEntity<ResponseDto> updateCountries(@Valid @RequestBody CountriesDto countriesDto)
	{
		ResponseDto countryDto =countryService.updateCountry(countriesDto);
		return new ResponseEntity<ResponseDto>(countryDto,HttpStatus.OK);
	}
	
	/**
     * Get a country by its ID.
     *
     * @param countryId the ID of the country
     * @return ResponseEntity with the country DTO
     */
	@GetMapping("/{countryId}") // dynamic value
	public ResponseEntity<CountriesDto> getProductsById(@PathVariable Integer countryId) {
		CountriesDto allcountries = countryService.getcountriesById(countryId);
		return new ResponseEntity<CountriesDto>(allcountries, HttpStatus.OK);
	}

	 /**
     * Get all countries.
     *
     * @return ResponseEntity with a list of country DTOs
     */
	@GetMapping
	public ResponseEntity<List<CountriesDto>> getAllCountries() {
		List<CountriesDto> allCountries = countryService.getAllCountries();
		return new ResponseEntity<List<CountriesDto>>(allCountries, HttpStatus.OK);
	}

	/**
     * Get the total number of customers per country.
     *
     * @return ResponseEntity with a list of customer counts per country DTO
     */
	@GetMapping("/count") 
	public ResponseEntity<List<CustomerByCountryDto>> getCountryWiseTotalCustomers() {
		List<CustomerByCountryDto> allCountries = countryService.countCustomersByCountry();
		return new ResponseEntity<List<CustomerByCountryDto>>(allCountries, HttpStatus.OK);
	}
	
	/**
     * Get the customer count per region.
     *
     * @param region the region name
     * @return ResponseEntity with a list of customer counts per region DTO
     */
	@GetMapping("/{region}/customers")
    public ResponseEntity<List<RegionCustomerCountDTO>> countCustomersByRegion(@PathVariable String region) {
        List<RegionCustomerCountDTO> customerCounts = countryService.countCustomersByRegion(region);
        return new ResponseEntity<List<RegionCustomerCountDTO>>(customerCounts,HttpStatus.OK);
    }
}
