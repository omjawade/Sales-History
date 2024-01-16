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

import com.saleshistory.dto.ChannelsDto;
import com.saleshistory.dto.CustomersDto;
import com.saleshistory.dto.ProductsDto;
import com.saleshistory.dto.ResponseDto;
import com.saleshistory.service.CustomersService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/customers")
public class CustomersController {

	@Autowired
	private CustomersService custService;

	/**
	 * Get all customers.
	 *
	 * @return ResponseEntity with a list of customer DTOs
	 */
	@GetMapping
	public ResponseEntity<List<CustomersDto>> getAllCustomers() {
		List<CustomersDto> allCustomers = custService.getAllCustomers();
		return new ResponseEntity<List<CustomersDto>>(allCustomers, HttpStatus.OK);
	}

	/**
	 * Get a customer by ID.
	 *
	 * @param custId the ID of the customer
	 * @return ResponseEntity with the customer DTO
	 */
	@GetMapping("/{custId}") // dynamic value
	public ResponseEntity<CustomersDto> getCustomersById(@PathVariable Integer custId) {
		CustomersDto employeeDto = custService.getCustomersById(custId);
		return new ResponseEntity<CustomersDto>(employeeDto, HttpStatus.OK);
	}

	/**
	 * Get customers by first name.
	 *
	 * @param custFirstName the first name of the customers
	 * @return ResponseEntity with a list of customer DTOs
	 */
	@GetMapping("/customername/{custFirstName}")
	public ResponseEntity<List<CustomersDto>> getCustomersByFirstName(@PathVariable String custFirstName) {
		List<CustomersDto> customersDto = custService.getCustomerByFirstName(custFirstName);
		return new ResponseEntity<List<CustomersDto>>(customersDto, HttpStatus.OK);
	}

	/**
	 * Get customers by city.
	 *
	 * @param custCity the city of the customers
	 * @return ResponseEntity with a list of customer DTOs
	 */
	@GetMapping("/customercity/{custCity}")
	public ResponseEntity<List<CustomersDto>> getCustomersByCustCity(@PathVariable String custCity) {
		List<CustomersDto> customersDto = custService.findByCustCity(custCity);
		return new ResponseEntity<List<CustomersDto>>(customersDto, HttpStatus.OK);
	}

	/**
	 * Get customers by income level.
	 *
	 * @param custIncomeLevel the income level of the customers
	 * @return ResponseEntity with a list of customer DTOs
	 */
	@GetMapping("/customersincome/{custIncomeLevel}")
	public ResponseEntity<List<CustomersDto>> getCustomersByCustIncomeLevel(
			@PathVariable("custIncomeLevel") String custIncomeLevel) {
		List<CustomersDto> customersDto = custService.findByCustIncomeLevel(custIncomeLevel);
		return new ResponseEntity<List<CustomersDto>>(customersDto, HttpStatus.OK);
	}

	/**
	 * Get customers within a credit limit range.
	 *
	 * @param beginCredit the beginning of the credit limit range
	 * @param endCredit   the end of the credit limit range
	 * @return ResponseEntity with a list of customer DTOs
	 */
	@GetMapping("/limit/{beginCredit}/{endCredit}")
	public ResponseEntity<List<CustomersDto>> getCustomersBetweenCreditLimit(
			@PathVariable("beginCredit") Integer beginCredit, @PathVariable("endCredit") Integer endCredit) {
		List<CustomersDto> customersDto = custService.getCustomersBetweenCreditLimit(beginCredit, endCredit);
		return new ResponseEntity<List<CustomersDto>>(customersDto, HttpStatus.OK);
	}

	/**
	 * Save a new customer.
	 *
	 * @param customersDto the DTO containing customer information
	 * @return ResponseEntity with the response DTO
	 */
	@PostMapping
	public ResponseEntity<ResponseDto> saveCustomer(@Valid @RequestBody CustomersDto customersDto) {
		ResponseDto customerDto = custService.addCustomer(customersDto);
		return new ResponseEntity<ResponseDto>(customerDto, HttpStatus.OK);
	}

	/**
	 * Update an existing customer.
	 *
	 * @param customerDto the DTO containing updated customer information
	 * @return ResponseEntity with the response DTO
	 */
	@PutMapping
	public ResponseEntity<ResponseDto> updateCustomer(@Valid @RequestBody CustomersDto customerDto) {
		ResponseDto custDt = custService.updateCustomer(customerDto);
		return new ResponseEntity<ResponseDto>(custDt, HttpStatus.OK);
	}

	/**
	 * Update the credit limit of a customer.
	 *
	 * @param custId       the ID of the customer
	 * @param customersDto the DTO containing the updated credit limit
	 * @return ResponseEntity with the response DTO
	 */
	@PutMapping("/{custId}/creditLimit")
	public ResponseEntity<ResponseDto> updateCustomerCreditLimit(@Valid @PathVariable Integer custId,
			@RequestBody CustomersDto customersDto) {
		ResponseDto updatedCustomer = custService.updateCustomerCreditLimit(custId, customersDto.getCustCreditLimit());
		return new ResponseEntity<ResponseDto>(updatedCustomer, HttpStatus.OK);
	}

	/**
	 * Get products sold to a customer.
	 *
	 * @param custId the ID of the customer
	 * @return ResponseEntity with a list of product DTOs
	 */
	@GetMapping("/soldproducts/{custId}")
	public ResponseEntity<List<ProductsDto>> getProductsSoldToCustomer(@PathVariable Integer custId) {
		List<ProductsDto> products = custService.getProductsSoldToCustomer(custId);
		return new ResponseEntity<List<ProductsDto>>(products, HttpStatus.OK);
	}

	/**
	 * Get channels used by a customer.
	 *
	 * @param custId the ID of the customer
	 * @return ResponseEntity with a list of channel DTOs
	 */
	@GetMapping("/channels/{custId}")
	public ResponseEntity<List<ChannelsDto>> getChannelsUsedByCustomer(@PathVariable Integer custId) {
		List<ChannelsDto> channelsUsedByCustomer = custService.getChannelsUsedByCustomer(custId);
		return new ResponseEntity<List<ChannelsDto>>(channelsUsedByCustomer, HttpStatus.OK);
	}

}
