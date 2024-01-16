package com.saleshistory.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saleshistory.dto.CountriesDto;
import com.saleshistory.dto.CustomerByCountryDto;
import com.saleshistory.dto.RegionCustomerCountDTO;
import com.saleshistory.dto.ResponseDto;
import com.saleshistory.entity.Countries;
import com.saleshistory.exceptions.CountriesException;
import com.saleshistory.repository.CountriesRepository;
import com.saleshistory.service.CountriesService;

@Service
public class CountriesServiceImpl implements CountriesService {

	@Autowired
	private CountriesRepository countryRepo;

	ResponseDto responseDto = new ResponseDto();

	@Override
	public ResponseDto addCountry(CountriesDto contriesDto) {

			Countries country = new Countries();
			BeanUtils.copyProperties(contriesDto, country);
			countryRepo.save(country);
			responseDto.setResponseMessage("Countries added Successfully");
			return responseDto;

	}

	@Override
	public ResponseDto updateCountry(CountriesDto countriesDto) {
		Optional<Countries> findById = countryRepo.findById(countriesDto.getCountryId());
		if (findById.isPresent()) {
			CountriesDto countriesDto2 = getcountriesById(countriesDto.getCountryId());
			Countries country = new Countries();
			BeanUtils.copyProperties(countriesDto, country);
			countryRepo.save(country);
			responseDto.setResponseMessage("Countries updated Successfully");
			return responseDto;
		}
		throw new CountriesException("Countries Not found for Id " + countriesDto.getCountryId());
	}

	@Override
	public CountriesDto getcountriesById(Integer Id) {
		Optional<Countries> findById = countryRepo.findById(Id);
		if (findById.isPresent()) {
			CountriesDto dto = new CountriesDto();
			BeanUtils.copyProperties(findById.get(), dto);
			return dto;
		} else {
			throw new CountriesException("Countries not found for Id " + Id);
		}
	}

	@Override
	public List<CountriesDto> getAllCountries() {
		List<Countries> countriesList = countryRepo.findAll();
		if (!countriesList.isEmpty()) {
			List<CountriesDto> countriesDtoList = new ArrayList<>();
			for (Countries countries : countriesList) {
				CountriesDto dto = new CountriesDto();
				BeanUtils.copyProperties(countries, dto);
				countriesDtoList.add(dto);
			}
			return countriesDtoList;
		}
		throw new CountriesException("Countries List is Empty");
	}

	@Override
	public List<CustomerByCountryDto> countCustomersByCountry() {
		List<CustomerByCountryDto> customerByCountryDtos = countryRepo.countCustomersByCountry();
		if (!customerByCountryDtos.isEmpty()) {
			return countryRepo.countCustomersByCountry();
		}
		throw new CountriesException("Customers not found ");

	}

	@Override
	public List<RegionCustomerCountDTO> countCustomersByRegion(String region) {
		List<RegionCustomerCountDTO> customerCounts = countryRepo.countCustomersByRegion(region);
		if (!customerCounts.isEmpty()) {
			return countryRepo.countCustomersByRegion(region);
		}
		throw new CountriesException("Customers Not found for region " + region);
	}

}
