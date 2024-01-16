package com.saleshistory.service;

import java.util.List;

import com.saleshistory.dto.CountriesDto;
import com.saleshistory.dto.CustomerByCountryDto;
import com.saleshistory.dto.RegionCustomerCountDTO;
import com.saleshistory.dto.ResponseDto;

public interface CountriesService {

	public List<CountriesDto> getAllCountries();

	public ResponseDto addCountry(CountriesDto contriesDto);

	public ResponseDto updateCountry(CountriesDto countriesDto);

	public CountriesDto getcountriesById(Integer Id);

	public List<RegionCustomerCountDTO> countCustomersByRegion(String region);

	List<CustomerByCountryDto> countCustomersByCountry();
}
