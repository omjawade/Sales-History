package com.saleshistory;

import static org.hamcrest.CoreMatchers.is;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saleshistory.dto.CountriesDto;
import com.saleshistory.dto.CustomerByCountryDto;
import com.saleshistory.dto.RegionCustomerCountDTO;
import com.saleshistory.dto.ResponseDto;
import com.saleshistory.service.CountriesService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username="omjawade",roles="admin")
class CountriesControllersTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CountriesService countriesService;

	
	//Positive Test Cases
	@Test
	public void testGetCountriesById() throws Exception {
		Integer countryId = 1;
		CountriesDto countriesDto = new CountriesDto();
		countriesDto.setCountryId(countryId);
		countriesDto.setCountryName("India");

		when(countriesService.getcountriesById(countryId)).thenReturn(countriesDto);

		mockMvc.perform(
				get("/countries/{countryId}", countryId).with(user("username").password("password").roles("ADMIN")))
				.andExpect(status().isOk()).andExpect(jsonPath("$.countryId", is(countryId))) // Verify the countryId
																								// property
				.andExpect(jsonPath("$.countryName", is("India"))); // Verify the countryName property

		verify(countriesService, times(1)).getcountriesById(countryId);
	}

	//Positive Test Cases
	@Test
	public void testGetAllCountries() throws Exception {

		CountriesDto countriesDto1 = new CountriesDto();
		countriesDto1.setCountryId(1);
		countriesDto1.setCountryName("Country 1");

		CountriesDto countriesDto2 = new CountriesDto();
		countriesDto2.setCountryId(2);
		countriesDto2.setCountryName("Country 2");

		List<CountriesDto> countriesDtoList = Arrays.asList(countriesDto1, countriesDto2);

		when(countriesService.getAllCountries()).thenReturn(countriesDtoList);

		mockMvc.perform(get("/countries").with(user("username").password("password").roles("ADMIN")))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].countryId", is(1))) // Verify the countryId
																							// property of the first
																							// item
				.andExpect(jsonPath("$[0].countryName", is("Country 1"))) // Verify the countryName property of the
																			// first item
				.andExpect(jsonPath("$[1].countryId", is(2))) // Verify the countryId property of the second item
				.andExpect(jsonPath("$[1].countryName", is("Country 2"))); // Verify the countryName property of the
																			// second item

		verify(countriesService, times(1)).getAllCountries();
	}

	//Positive Test Cases
	@Test
	public void testGetCountryWiseTotalCustomers() throws Exception {
		CustomerByCountryDto costemerByCountryDto = new CustomerByCountryDto() {

			@Override
			public Long getCustomerCount() {
				return 23L;
			}

			@Override
			public String getCountryName() {

				return "Singapore";
			}

			@Override
			public void setCountryName(String string) {

			}

			@Override
			public void setCustomerCount(long l) {

			}
		};
		List<CustomerByCountryDto> byCountryDtosList = Arrays.asList(costemerByCountryDto);
		when(countriesService.countCustomersByCountry()).thenReturn(byCountryDtosList);
		mockMvc.perform(get("/countries/count").with(user("username").password("password").roles("ADMIN")))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].customerCount", is(23)))
				.andExpect(jsonPath("$[0].countryName", is("Singapore")));

		verify(countriesService, times(1)).countCustomersByCountry();
	}

	//Positive Test Cases
	@Test
	public void testCountCustomersByRegion() throws Exception {

		String region = "Asia";
		RegionCustomerCountDTO customerCountDTO = new RegionCustomerCountDTO() {

			@Override
			public String getRegion() {

				return "Asia";
			}

			@Override
			public Long getCustomerCount() {

				return 7L;
			}
		};

		List<RegionCustomerCountDTO> regionCustomerCountDTO = Arrays.asList(customerCountDTO);
		when(countriesService.countCustomersByRegion("Asia")).thenReturn(regionCustomerCountDTO);
		mockMvc.perform(
				get("/countries/{region}/customers", region).with(user("username").password("password").roles("ADMIN")))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].customerCount", is(7)))

				.andExpect(jsonPath("$[0].region", is("Asia")));
	}

	//Positive Test Cases
	@Test
	public void testSaveCountry() throws Exception {

		Integer countryId = 1;
		ResponseDto responseDto = new ResponseDto();
		CountriesDto requestDto = new CountriesDto();
		requestDto.setCountryId(countryId);
		requestDto.setCountryName("India");
		requestDto.setCountryRegionId(2);
		requestDto.setCountrySubregionId(2);
		requestDto.setCountryTotalId(1);
		responseDto.setResponseMessage("Country added successfully");
		when(countriesService.addCountry(any(CountriesDto.class))).thenReturn(responseDto);

		mockMvc.perform(post("/countries").with(user("username").password("password").roles("ADMIN"))
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(requestDto)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.responseMessage").value("Country added successfully"));
	}
	
	//Negative Test Cases
	@Test
	public void testSaveCountry_InvalidInput() throws Exception {

		Integer countryId = 1;
		ResponseDto responseDto = new ResponseDto();
		CountriesDto requestDto = new CountriesDto();
		requestDto.setCountryId(countryId);
		requestDto.setCountryName("India");
		requestDto.setCountrySubregionId(2);
		requestDto.setCountryTotalId(1);
		
		when(countriesService.addCountry(any(CountriesDto.class))).thenReturn(responseDto);

		mockMvc.perform(post("/countries").with(user("username").password("password").roles("ADMIN"))
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(requestDto)))
				.andExpect(status().isBadRequest());
				
	}

	//Positive Test Cases
	@Test
	public void testUpdateCountries() throws Exception {

		Integer countryId = 1;
		CountriesDto countriesDto = new CountriesDto();
		countriesDto.setCountryId(countryId);
		countriesDto.setCountryName("Asia");
		countriesDto.setCountryRegionId(1234);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResponseMessage("Country updated successfully");
		when(countriesService.updateCountry(any(CountriesDto.class))).thenReturn(responseDto);

		mockMvc.perform(put("/countries").with(user("username").password("password").roles("ADMIN"))
				.contentType(MediaType.APPLICATION_JSON).content(
						"{\"countryId\":1,\"countryName\":\"Country1\",\"countryIsoCode\":\"C1\",\"countrySubregion\":\"Subregion1\",\"countrySubregionId\":1,\"countryRegion\":\"Region1\",\"countryRegionId\":1,\"countryTotal\":\"Total1\",\"countryTotalId\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.responseMessage").value("Country updated successfully"));
	}
	
	//Negative Test Cases
	@Test
	public void testUpdateCountries_InvalidInput() throws Exception {

		Integer countryId = 1;
		CountriesDto countriesDto = new CountriesDto();
		countriesDto.setCountryId(countryId);
		countriesDto.setCountryName("Asia");
		ResponseDto responseDto = new ResponseDto();
		when(countriesService.updateCountry(any(CountriesDto.class))).thenReturn(responseDto);

		mockMvc.perform(put("/countries").with(user("username").password("password").roles("ADMIN"))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	//Negative Test Cases
	@Test
	    public void testGetAllCountries_EmptyList() throws Exception {
	       
	        when(countriesService.getAllCountries()).thenReturn(Collections.emptyList());

	       
	        mockMvc.perform(get("/countries").with(user("username").password("password").roles("ADMIN")))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$").isEmpty());

	        
	        verify(countriesService, times(1)).getAllCountries();
	    }
	 

}
