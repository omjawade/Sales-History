package com.saleshistory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saleshistory.config.jwt.JwtUtils;
import com.saleshistory.dto.ChannelsDto;
import com.saleshistory.dto.CountriesDto;
import com.saleshistory.dto.CustomersDto;
import com.saleshistory.dto.ProductsDto;
import com.saleshistory.dto.ResponseDto;
import com.saleshistory.exceptions.ChannelsException;
import com.saleshistory.exceptions.CustomerNotFoundException;
import com.saleshistory.service.CustomersService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomersControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomersService custService;

	@Autowired
	private JwtUtils jwtUtils;

	CountriesDto countries;

	
	//Positive Test Case
	@Test
	public void testGetAllCustomers() throws Exception {
		Integer countryId=1;
		CustomersDto customersDto = new CustomersDto();
		customersDto.setCountryId(countryId);
		customersDto.setCustCity("NewYork");
		customersDto.setCustCityId(123);

		List<CustomersDto> customerDtoList = Arrays.asList(customersDto);

		when(custService.getAllCustomers()).thenReturn(customerDtoList);
		

		mockMvc.perform(MockMvcRequestBuilders.get("/customers").with(user("username").password("password").roles("ADMIN")))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].countryId").value(1))
		.andExpect(jsonPath("$[0].custCityId").value(123))
		.andExpect(jsonPath("$[0].custCity").value("NewYork"));
		verify(custService, times(1)).getAllCustomers();
	}
	
	//Positive Test Case
	@Test
	public void testGetCustomersByFirstName() throws Exception {
		String custFirstName = "Anne";
		CustomersDto customersDto = new CustomersDto();
		List<CustomersDto> customerDtoList = Arrays.asList(customersDto);
		customersDto.setCustFirstName(custFirstName);
		when(custService.getCustomerByFirstName(custFirstName)).thenReturn(customerDtoList);

		mockMvc.perform(get("/customers/customername/{custFirstName}", custFirstName).with(user("username").password("password").roles("ADMIN"))).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].custFirstName").value("Anne"));

		verify(custService, times(1)).getCustomerByFirstName(custFirstName);

	}

	
	//Positive Test Case
	@Test
	public void testGetCustomersByCustCity() throws Exception {
		String custCity = "yavatmal";
		CustomersDto customersDto = new CustomersDto();
		List<CustomersDto> customerDtoList = Arrays.asList(customersDto);
		customersDto.setCustCity(custCity);
		when(custService.findByCustCity(custCity)).thenReturn(customerDtoList);
		mockMvc.perform(get("/customers/customercity/{custCity}", custCity).with(user("username").password("password").roles("ADMIN"))).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].custCity").value("yavatmal"));

		verify(custService, times(1)).findByCustCity(custCity);
	}

	//Positive Test Case
	@Test
	public void testGetCustomersByCustIncomeLevel() throws Exception {
		String custIncomeLevel = "G: 130,000 - 149,999";
		CustomersDto customersDto = new CustomersDto();
		List<CustomersDto> customerDtoList = Arrays.asList(customersDto);
		customersDto.setCustIncomeLevel(custIncomeLevel);
		when(custService.findByCustIncomeLevel(custIncomeLevel)).thenReturn(customerDtoList);
		mockMvc.perform(get("/customers/customersincome/{custIncomeLevel}", custIncomeLevel).with(user("username").password("password").roles("ADMIN"))).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].custIncomeLevel").value("G: 130,000 - 149,999"));

		verify(custService, times(1)).findByCustIncomeLevel(custIncomeLevel);
	}

	//Positive Test Case
	@Test
	public void testSaveCustomer() throws Exception {
	    // Mock the service response
	    ResponseDto responseDto = new ResponseDto();
	    responseDto.setResponseMessage("Customer added Successfully");
	    when(custService.addCustomer(any(CustomersDto.class))).thenReturn(responseDto);

	    // Prepare the request body
	    CustomersDto requestDto = new CustomersDto();
	    requestDto.setCustId(1);
	    requestDto.setCustYearOfBirth(2019);
	    requestDto.setCustCityId(1);
	    requestDto.setCustStateProvinceId(1);
	    requestDto.setCountryId(1);
	    requestDto.setCustTotalId(1);
	    requestDto.setCustMainPhoneNumber("8855950040");
	    requestDto.setCustEmail("omjawade08@gmail.com");

	    
	    // Set other properties according to your requirements

	    // Perform the POST request
	    mockMvc.perform(post("/customers").with(user("username").password("password").roles("ADMIN"))
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(new ObjectMapper().writeValueAsString(requestDto)))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.responseMessage").value("Customer added Successfully"));
	}
	
	//Invalid Input Test Case
	@Test
	public void testSaveCustomer_InvalidInput() throws Exception {
	    // Mock the service response
	    ResponseDto responseDto = new ResponseDto();
	    responseDto.setResponseMessage("Customer added Successfully");
	    when(custService.addCustomer(any(CustomersDto.class))).thenReturn(responseDto);

	    // Prepare the request body with invalid input (e.g., missing required fields)
	    CustomersDto requestDto = new CustomersDto();
	    // Set only some properties, leaving out required fields

	    // Perform the POST request
	    mockMvc.perform(post("/customers").with(user("username").password("password").roles("ADMIN"))
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(new ObjectMapper().writeValueAsString(requestDto)))
	            .andExpect(status().isBadRequest()); // Expecting a bad request status code
	}

	
	//Positive Test Case
	@Test
	public void testUpdateCustomer() throws Exception {
		// Mock the service response
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResponseMessage("Customer details updated successfully");
		when(custService.updateCustomer(any(CustomersDto.class))).thenReturn(responseDto);

		// Perform the PUT request
		mockMvc.perform(put("/customers").with(user("username").password("password").roles("ADMIN")).contentType(MediaType.APPLICATION_JSON).content(
				"{\"custId\":1,\"custFirstName\":\"John\",\"custLastName\":\"Doe\",\"custGender\":\"M\",\"custYearOfBirth\":1990,\"custMaritalStatus\":\"Single\",\"custStreetAddress\":\"123 Main St\",\"custPostalCode\":\"12345\",\"custCity\":\"New York\",\"custCityId\":1,\"custStateProvince\":\"New York\",\"custStateProvinceId\":1,\"countryId\":1,\"custMainPhoneINT\":\"123-456-789\",\"custIncomeLevel\":\"High\",\"custCreditLimit\":1000,\"custEmail\":\"john.doe@example.com\",\"custTotal\":\"Total1\",\"custTotalId\":1,\"custSrcId\":1,\"custEffFrom\":\"2022-01-01\",\"custEffTo\":\"2023-01-01\",\"custValid\":\"Y\",\"custMainPhoneNumber\":\"1234567890\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.responseMessage").value("Customer details updated successfully"));
	}
	
	//Negative Test Case
	@Test
	public void testUpdateCustomer_CustomerNotFound() throws Exception {
	    // Mock the service response
	    when(custService.updateCustomer(any(CustomersDto.class))).thenThrow(new CustomerNotFoundException("Customer Not Found"));

	    // Perform the PUT request
	    mockMvc.perform(put("/customers").with(user("username").password("password").roles("ADMIN")).contentType(MediaType.APPLICATION_JSON).content(
	            "{\"custId\":1,\"custFirstName\":\"John\",\"custLastName\":\"Doe\",\"custGender\":\"M\",\"custYearOfBirth\":1990,\"custMaritalStatus\":\"Single\",\"custStreetAddress\":\"123 Main St\",\"custPostalCode\":\"12345\",\"custCity\":\"New York\",\"custCityId\":1,\"custStateProvince\":\"New York\",\"custStateProvinceId\":1,\"countryId\":1,\"custMainPhoneINT\":\"123-456-789\",\"custIncomeLevel\":\"High\",\"custCreditLimit\":1000,\"custEmail\":\"john.doe@example.com\",\"custTotal\":\"Total1\",\"custTotalId\":1,\"custSrcId\":1,\"custEffFrom\":\"2022-01-01\",\"custEffTo\":\"2023-01-01\",\"custValid\":\"Y\",\"custMainPhoneNumber\":\"1234567890\"}"))
	            .andExpect(status().isNotFound()); // Expecting a not found status code
	}


	//Positive Test Case
	@Test
	public void testUpdateCustomerCreditLimit() throws Exception {
		// Mock the service response
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResponseMessage("Credit Limit Updated Successfully");
		when(custService.updateCustomerCreditLimit(1, 2000)).thenReturn(responseDto);

		// Perform the PUT request
		mockMvc.perform(put("/customers/1/creditLimit").with(user("username").password("password").roles("ADMIN")).contentType(MediaType.APPLICATION_JSON)
				.content("{\"custCreditLimit\":2000}")).andExpect(status().isOk())
				.andExpect(jsonPath("$.responseMessage").value("Credit Limit Updated Successfully"));
	}
	
	//Negative Test Case
	@Test
	public void testUpdateCustomerCreditLimit_CustomerNotFound() throws Exception {
	    // Mock the service response
	    when(custService.updateCustomerCreditLimit(1, 2000)).thenThrow(new CustomerNotFoundException("Customer Not Found"));

	    // Perform the PUT request
	    mockMvc.perform(put("/customers/1/creditLimit").with(user("username").password("password").roles("ADMIN")).contentType(MediaType.APPLICATION_JSON)
	            .content("{\"custCreditLimit\":2000}")).andExpect(status().isNotFound()); // Expecting a not found status code
	}


	//Positive Test Case
	@Test
	public void testGetCustomersBetweenCreditLimit() throws Exception {
		Integer beginCredit = 1000;
		Integer endCredit = 11000;
		CustomersDto customersDto = new CustomersDto();
		customersDto.setCustId(1);
		customersDto.setCustFirstName("anne");
		customersDto.setCustLastName("Doe");
		List<CustomersDto> customersDtoList = Arrays.asList(customersDto);
		when(custService.getCustomersBetweenCreditLimit(beginCredit, endCredit)).thenReturn(customersDtoList);

		mockMvc.perform(get("/customers/limit/{beginCredit}/{endCredit}", beginCredit, endCredit).with(user("username").password("password").roles("ADMIN")))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].custId", is(1))) // Verify the custId property of
																						// the first item
				.andExpect(jsonPath("$[0].custFirstName", is("anne"))) // Verify the custFirstName property of the first
																		// item
				.andExpect(jsonPath("$[0].custLastName", is("Doe"))); // Verify the custLastName property of the first
																		// item;

		verify(custService, times(1)).getCustomersBetweenCreditLimit(beginCredit, endCredit);
	}
	
	//Negative Test Case
	@Test
	public void testGetProductsSoldToCustomer_CustomerNotFound() throws Exception {
	    // Mock the service response
	    when(custService.getProductsSoldToCustomer(1)).thenThrow(new CustomerNotFoundException("Customer Not Found"));

	    // Perform the GET request
	    mockMvc.perform(get("/customers/soldproducts/{custId}", 1).with(user("username").password("password").roles("ADMIN"))).andExpect(status().isNotFound()); // Expecting a not found status code
	}


	//Positive Test Case
	@Test
	public void testGetProductsSoldToCustomer() throws Exception {
		// Mock data
		ProductsDto product1 = new ProductsDto();
		product1.setProdId(1);
		product1.setProdName("Product 1");
		product1.setProdDesc("Description 1");

		ProductsDto product2 = new ProductsDto();
		product2.setProdId(2);
		product2.setProdName("Product 2");
		product2.setProdDesc("Description 2");

		List<ProductsDto> products = Arrays.asList(product1, product2);

		// Mock the service method
		when(custService.getProductsSoldToCustomer(1)).thenReturn(products);

		// Perform the GET request
		mockMvc.perform(get("/customers/soldproducts/{custId}", 1).with(user("username").password("password").roles("ADMIN"))).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].prodId", is(1))).andExpect(jsonPath("$[0].prodName", is("Product 1")))
				.andExpect(jsonPath("$[0].prodDesc", is("Description 1"))).andExpect(jsonPath("$[1].prodId", is(2)))
				.andExpect(jsonPath("$[1].prodName", is("Product 2")))
				.andExpect(jsonPath("$[1].prodDesc", is("Description 2")));

		verify(custService, times(1)).getProductsSoldToCustomer(1);
	}

	//Positive Test Case
	@Test
	public void testGetChannelsUsedByCustomer() throws Exception {
		Integer custId = 1;

		List<ChannelsDto> channelsDtoList = new ArrayList<>();
		ChannelsDto channelsDto1 = new ChannelsDto();
		channelsDto1.setChannelId(1);
		channelsDto1.setChannelDesc("Channel 1");
		channelsDtoList.add(channelsDto1);

		when(custService.getChannelsUsedByCustomer(custId)).thenReturn(channelsDtoList);

		mockMvc.perform(get("/customers/channels/{custId}", custId).with(user("username").password("password").roles("ADMIN"))).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].channelId", is(1))) // Verify the channelId property of the first item
				.andExpect(jsonPath("$[0].channelDesc", is("Channel 1"))); // Verify the channelDesc property of the
																			// first item

		verify(custService, times(1)).getChannelsUsedByCustomer(custId);
	}
	
	//Negative Test Case
	@Test
	public void testGetChannelsUsedByCustomer_ChannelsNotFound() throws Exception {
	    Integer custId = 1;

	    // Mock the service response
	    when(custService.getChannelsUsedByCustomer(custId)).thenThrow(new ChannelsException("No Channels Found"));

	    // Perform the GET request
	    mockMvc.perform(get("/customers/channels/{custId}", custId).with(user("username").password("password").roles("ADMIN"))).andExpect(status().isNotFound()); // Expecting a not found status code
	}

}