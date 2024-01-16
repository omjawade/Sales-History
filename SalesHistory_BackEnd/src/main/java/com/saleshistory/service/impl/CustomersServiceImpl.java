package com.saleshistory.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saleshistory.dto.ChannelsDto;
import com.saleshistory.dto.CustomersDto;
import com.saleshistory.dto.ProductsDto;
import com.saleshistory.dto.ResponseDto;
import com.saleshistory.entity.Channels;
import com.saleshistory.entity.Customers;
import com.saleshistory.entity.Products;
import com.saleshistory.exceptions.ChannelsException;
import com.saleshistory.exceptions.CustomerNotFoundException;
import com.saleshistory.repository.CountriesRepository;
import com.saleshistory.repository.CustomersRepository;
import com.saleshistory.service.CustomersService;

@Service
public class CustomersServiceImpl implements CustomersService {

	@Autowired
	private CustomersRepository custRepo;
	@Autowired
	private CountriesRepository countRepo;

	ResponseDto responseDto = new ResponseDto();

	@Override
	public List<CustomersDto> getAllCustomers() {
		List<Customers> customersList = custRepo.findAll();
		if (!customersList.isEmpty()) {
			List<CustomersDto> custDtoList = new ArrayList<>();
			for (Customers customer : customersList) {
				CustomersDto dto = new CustomersDto();
				dto.setCountryId(customer.getCountryId().getCountryId());
				BeanUtils.copyProperties(customer, dto);
				custDtoList.add(dto);
			}
			return custDtoList;
		}
		throw new CustomerNotFoundException("List is Empty");
	}

	@Override
	public ResponseDto updateCustomer(CustomersDto customersDto) {
		Optional<Customers> findById = custRepo.findById(customersDto.getCustId());
		if (findById.isPresent()) {
			CustomersDto customersDto2 = getCustomersById(customersDto.getCustId());
			Customers cust = new Customers();
			cust.setCountryId(countRepo.findById(customersDto.getCountryId()).get());
			BeanUtils.copyProperties(customersDto, cust);
			custRepo.save(cust);
			responseDto.setResponseMessage("Customers Detail updated Successfully");
			return responseDto;
		}
		throw new CustomerNotFoundException("Customer with Id " + customersDto.getCustId() + " is not present");
	}

	@Override
	public CustomersDto getCustomersById(Integer Id) {
		Optional<Customers> findById = custRepo.findById(Id);
		if (findById.isPresent()) {
			CustomersDto dto = new CustomersDto();
			BeanUtils.copyProperties(findById.get(), dto);
			dto.setCountryId(findById.get().getCountryId().getCountryId());
			return dto;
		}
		throw new CustomerNotFoundException("Customer Not found for id " + Id);
	}

	@Override
	public List<CustomersDto> getCustomerByFirstName(String custFirstName) {
		List<Customers> customersList = custRepo.findByCustFirstName(custFirstName);
		if (!customersList.isEmpty()) {
			List<CustomersDto> custDtoList = new ArrayList<>();
			for (Customers customer : customersList) {
				CustomersDto dto = new CustomersDto();
				dto.setCountryId(customer.getCountryId().getCountryId());
				BeanUtils.copyProperties(customer, dto);
				custDtoList.add(dto);
			}
			return custDtoList;
		}
		throw new CustomerNotFoundException("Customer having First name " + custFirstName + " is not present.");
	}

	@Override
	public List<CustomersDto> findByCustCity(String custCity) {
		List<Customers> customersList = custRepo.findByCustCity(custCity);
		if (!customersList.isEmpty()) {
			List<CustomersDto> custDtoList = new ArrayList<>();
			for (Customers customer : customersList) {
				CustomersDto dto = new CustomersDto();
				dto.setCountryId(customer.getCountryId().getCountryId());
				BeanUtils.copyProperties(customer, dto);
				custDtoList.add(dto);
			}
			return custDtoList;
		}
		throw new CustomerNotFoundException(custCity + " city is not present.");
	}

	@Override
	public ResponseDto addCustomer(CustomersDto customersDto) {
		if (existsbyCustEmail(customersDto.getCustEmail())) {
			System.out.println("Customer already exists");
			throw new CustomerNotFoundException("Customer already Exists");
		}
		Customers customer = new Customers();
		customer.setCountryId(countRepo.findById(customersDto.getCountryId()).get());
		BeanUtils.copyProperties(customersDto, customer);
		custRepo.save(customer);
		responseDto.setResponseMessage("Customer added Successfully");
		return responseDto;

	}

	@Override
	public Boolean existsbyCustEmail(String custEmail) {
		Boolean existsByCustEmailID = custRepo.existsByCustEmail(custEmail);
		return existsByCustEmailID;
	}

	@Override
	public List<CustomersDto> findByCustIncomeLevel(String custIncomeLevel) {
		List<Customers> customersList = custRepo.findByCustIncomeLevel(custIncomeLevel);
		if (!customersList.isEmpty()) {
			List<CustomersDto> custDtoList = new ArrayList<>();
			for (Customers customer : customersList) {
				CustomersDto dto = new CustomersDto();
				dto.setCountryId(customer.getCountryId().getCountryId());
				BeanUtils.copyProperties(customer, dto);
				custDtoList.add(dto);
			}
			return custDtoList;
		}
		throw new CustomerNotFoundException("Customer not found for " + custIncomeLevel + " income level.");
	}

	@Override
	public List<CustomersDto> getCustomersBetweenCreditLimit(Integer beginCredit, Integer endCredit) {
		List<Customers> customersList = custRepo.getCustomersBetweenCreditLimit(beginCredit, endCredit);
		if (!customersList.isEmpty()) {
			List<CustomersDto> custDtoList = new ArrayList<>();
			for (Customers customer : customersList) {
				CustomersDto dto = new CustomersDto();
				dto.setCountryId(customer.getCountryId().getCountryId());
				BeanUtils.copyProperties(customer, dto);
				custDtoList.add(dto);
			}
			return custDtoList;
		}
		throw new CustomerNotFoundException(
				"Customers not found for beginCredit: " + beginCredit + " and endCredits: " + endCredit);
	}

	@Override
	public ResponseDto updateCustomerCreditLimit(Integer custId, Integer custCreditLimit) {
		Customers customer = custRepo.findById(custId)
				.orElseThrow(() -> new RuntimeException("Customer not found with ID: " + custId));
		customer.setCustCreditLimit(custCreditLimit);
		customer.setCustId(custId);
		Customers updatedCustomer = custRepo.save(customer);
		CustomersDto dto = new CustomersDto();
		BeanUtils.copyProperties(updatedCustomer, dto);
		responseDto.setResponseMessage("Credit Limit Updated Successfully");
		return responseDto;
	}

	public List<ProductsDto> getProductsSoldToCustomer(Integer custId) {
		List<Object[]> results = custRepo.getProductsSoldToCustomer(custId);
		if (!results.isEmpty()) {
			List<ProductsDto> productsDtoList = new ArrayList<>();

			for (Object[] result : results) {
				ProductsDto dto = new ProductsDto();
				dto.setProdId((Integer) result[0]);
				dto.setProdName((String) result[1]);
				dto.setProdDesc((String) result[2]);
				dto.setProdSubcategory((String) result[3]);
				dto.setProdSubcategoryId((Integer) result[4]);
				dto.setProdSubcategoryDesc((String) result[5]);
				dto.setProdCategory((String) result[6]);
				dto.setProdCategoryId((Integer) result[7]);
				dto.setProdCategoryDesc((String) result[8]);
				dto.setProdWeightClass((Integer) result[9]);
				dto.setProdUnitOfMeasure((String) result[10]);
				dto.setProdPackSize((String) result[11]);
				dto.setSupplierId((Integer) result[12]);
				dto.setProdStatus((String) result[13]);
				dto.setProdListPrice((Double) result[14]);
				dto.setProdMinPrice((Double) result[15]);
				dto.setProdTotal((String) result[16]);
				dto.setProdTotalId((Integer) result[17]);
				dto.setProdSrcId((Integer) result[18]);
//			dto.setProdEffFrom((LocalDate) result[19]);
//			dto.setProdEffTo((LocalDate) result[20]);
				dto.setProdValid((Character) result[21]);

				productsDtoList.add(dto);
			}

			return productsDtoList;
		}
		throw new CustomerNotFoundException("Products Not found Sold to Customers");
	}

	@Override
	public List<ChannelsDto> getChannelsUsedByCustomer(Integer custId) {
		List<Channels> list = custRepo.getChannelsUsedByCustomer(custId);
		if (!list.isEmpty()) {
			List<ChannelsDto> dtos = new ArrayList<>();
			for (Channels ch : list) {
				ChannelsDto dto = new ChannelsDto();
				BeanUtils.copyProperties(ch, dto);
				dtos.add(dto);
			}
			return dtos;
		}
		throw new ChannelsException("Customers has not brought any product");
	}

}
