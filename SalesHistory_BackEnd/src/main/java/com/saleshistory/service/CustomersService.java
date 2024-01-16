package com.saleshistory.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.saleshistory.dto.ChannelsDto;
import com.saleshistory.dto.CustomersDto;
import com.saleshistory.dto.ProductsDto;
import com.saleshistory.dto.ResponseDto;
import com.saleshistory.entity.Channels;

public interface CustomersService {

	public ResponseDto addCustomer(CustomersDto customersDto);

	public List<CustomersDto> getAllCustomers();

	public ResponseDto updateCustomer(CustomersDto customersDto);

	public CustomersDto getCustomersById(Integer Id);

	public List<CustomersDto> getCustomerByFirstName(String custFirstName);

	public List<CustomersDto> findByCustCity(String custCity);

	Boolean existsbyCustEmail(String custEmail);

	public List<CustomersDto> findByCustIncomeLevel(String custIncomeLevel);

	public List<CustomersDto> getCustomersBetweenCreditLimit(Integer beginCredit, Integer endCredit);

	public ResponseDto updateCustomerCreditLimit(Integer custCreditLimit, Integer custId);

	public List<ProductsDto> getProductsSoldToCustomer(Integer custId);
	
	public List<ChannelsDto> getChannelsUsedByCustomer(Integer custId);
}
