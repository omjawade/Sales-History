package com.saleshistory;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.saleshistory.dto.MostSoldProductDto;
import com.saleshistory.dto.QuantityByCategoryDto;
import com.saleshistory.dto.QuantityByCategoryForYearDto;
import com.saleshistory.dto.QuantityBySoldCategoryForYearDto;
import com.saleshistory.dto.QuantitySoldByCategoryDto;
import com.saleshistory.dto.SalesDto;
import com.saleshistory.exceptions.SalesException;
import com.saleshistory.service.SalesService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SalesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SalesService saleService;

	// Positive Test Cases
	@Test
	public void testGetAllSales() throws Exception {

		SalesDto salesDto1 = new SalesDto();
		salesDto1.setProdId(1);
		salesDto1.setCustId(2);
		salesDto1.setAmountSold(100.0);

		SalesDto salesDto2 = new SalesDto();
		salesDto2.setProdId(3);
		salesDto2.setCustId(4);
		salesDto2.setAmountSold(200.0);
		List<SalesDto> mockSales = Arrays.asList(salesDto1, salesDto2);

		// Mock service method
		when(saleService.getAllSales()).thenReturn(mockSales);

		// Perform GET request
		mockMvc.perform(get("/sales").with(user("username").password("password").roles("ADMIN")))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].prodId", is(1)))
				.andExpect(jsonPath("$[0].custId", is(2))).andExpect(jsonPath("$[0].amountSold", is(100.0)))
				.andExpect(jsonPath("$[1].prodId", is(3))).andExpect(jsonPath("$[1].custId", is(4)))
				.andExpect(jsonPath("$[1].amountSold", is(200.0)));

		// Verify that the service method is called
		verify(saleService, times(1)).getAllSales();
	}

	@Test
    public void testGetAllSales_SalesListEmpty() throws Exception {
        // Mock the service response to throw an exception
        when(saleService.getAllSales())
                .thenThrow(new SalesException("Sales List is Empty"));

        // Perform the GET request and validate the response
        mockMvc.perform(get("/sales").with(user("username").password("password").roles("ADMIN")))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value("Sales List is Empty"));

        // Verify that the service method was called
        verify(saleService, times(1)).getAllSales();
    }

	// Positive Test Cases
	@Test
	public void testGetMostSoldProducts() throws Exception {
		// Mock the service response
		MostSoldProductDto mostSoldProductDto = new MostSoldProductDto() {

			@Override
			public Long getSalesCount() {
				// TODO Auto-generated method stub
				return 10L;
			}

			@Override
			public String getProdName() {
				// TODO Auto-generated method stub
				return "Product";
			}

			@Override
			public Integer getProdListPrice() {
				// TODO Auto-generated method stub
				return 100;
			}

			@Override
			public Long getProdId() {
				// TODO Auto-generated method stub
				return 123L;
			}

			@Override
			public String getProdDesc() {
				// TODO Auto-generated method stub
				return "Category desc";
			}

			@Override
			public String getProdCategory() {
				// TODO Auto-generated method stub
				return "Category1";
			}
		};
		List<MostSoldProductDto> quantitiesByCategory = Arrays.asList(mostSoldProductDto);
		when(saleService.findMostSoldProducts()).thenReturn(quantitiesByCategory);

		// Perform the GET request
		mockMvc.perform(get("/sales/most").with(user("username").password("password").roles("ADMIN")))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].prodCategory", is("Category1")))
				.andExpect(jsonPath("$[0].prodDesc", is("Category desc"))).andExpect(jsonPath("$[0].prodId", is(123)))
				.andExpect(jsonPath("$[0].prodListPrice", is(100))).andExpect(jsonPath("$[0].prodName", is("Product")))
				.andExpect(jsonPath("$[0].salesCount", is(10)));
	}

	// Negative Test Cases
	@Test
	public void testGetMostSoldProducts_NoProductsFound() throws Exception {
	    // Mock the service response to throw an exception
	    when(saleService.findMostSoldProducts())
	            .thenThrow(new SalesException("No most sold products found"));

	    // Perform the GET request and validate the response
	    mockMvc.perform(get("/sales/most").with(user("username").password("password").roles("ADMIN")))
	            .andExpect(status().isNotFound())
	            .andExpect(jsonPath("$.errorMessage").value("No most sold products found"));

	    // Verify that the service method was called
	    verify(saleService, times(1)).findMostSoldProducts();
	}

	// Positive Test Cases
	@Test
	public void testGetSalesByQuarter() throws Exception {
		String quarter = "2021-02";

		List<SalesDto> salesByQuarter = new ArrayList<>(); // Add your expected sales by quarter

		Mockito.when(saleService.getSalesByQuarter(quarter)).thenReturn(salesByQuarter);

		mockMvc.perform(MockMvcRequestBuilders.get("/sales/quarter").param("quarter", quarter)
				.with(user("username").password("password").roles("ADMIN")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void testGetSalesByQuarter_SalesNotFound() throws Exception {
		// Mock the service response to throw an exception
		String quarter = "2022-Q1";
		when(saleService.getSalesByQuarter(quarter))
				.thenThrow(new SalesException("Sales Not found for quarter " + quarter));

		// Perform the GET request and validate the response
		mockMvc.perform(get("/sales/quarter").param("quarter", quarter)
				.with(user("username").password("password").roles("ADMIN"))).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.errorMessage").value("Sales Not found for quarter " + quarter));

		// Verify that the service method was called
		verify(saleService, times(1)).getSalesByQuarter(quarter);
	}

	// Positive Test Cases
	@Test
	public void testGetSalesByDate() throws Exception {
		LocalDate date = LocalDate.parse("2023-03-19");

		List<SalesDto> salesByDate = new ArrayList<>(); // Add your expected sales by date

		Mockito.when(saleService.findByDate(date)).thenReturn(salesByDate);

		mockMvc.perform(MockMvcRequestBuilders.get("/sales/date").param("date", date.toString())
				.with(user("username").password("password").roles("ADMIN")).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	// Negative Test Cases
	@Test
	public void testGetSalesByDate_SalesNotFound() throws Exception {
		// Mock the service response to throw an exception
		LocalDate date = LocalDate.parse("2023-03-19");
		when(saleService.findByDate(date)).thenThrow(new SalesException("Sales not found for date " + date));

		// Perform the GET request and validate the response
		mockMvc.perform(get("/sales/date").param("date", date.toString())
				.with(user("username").password("password").roles("ADMIN"))).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.errorMessage").value("Sales not found for date " + date));

		// Verify that the service method was called
		verify(saleService, times(1)).findByDate(date);
	}

	// Positive Test Cases
	@Test
	public void testGetTotalQuantitiesByCategory() throws Exception {
		// Mock the service response
		QuantityByCategoryDto quantityByCategoryDto = new QuantityByCategoryDto() {

			@Override
			public Long getSalesCount() {
				// TODO Auto-generated method stub
				return 10L;
			}

			@Override
			public String getProdCategory() {
				// TODO Auto-generated method stub
				return "Category1";
			}
		};
		List<QuantityByCategoryDto> quantitiesByCategory = Arrays.asList(quantityByCategoryDto);
		when(saleService.countTotalQuantitiesByCategory()).thenReturn(quantitiesByCategory);

		// Perform the GET request
		mockMvc.perform(get("/sales/qtys/categorywise").with(user("username").password("password").roles("ADMIN")))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].prodCategory", is("Category1")))
				.andExpect(jsonPath("$[0].salesCount", is(10)));
	}

	//Negative Test Cases
	@Test
	    public void testGetTotalQuantitiesByCategory_QuantitiesNotFound() throws Exception {
	        // Mock the service response to throw an exception
	        when(saleService.countTotalQuantitiesByCategory())
	                .thenThrow(new SalesException("No Count is Present for Category"));

	        // Perform the GET request and validate the response
	        mockMvc.perform(get("/sales/qtys/categorywise").with(user("username").password("password").roles("ADMIN")))
	                .andExpect(status().isNotFound())
	                .andExpect(jsonPath("$.errorMessage").value("No Count is Present for Category"));

	        // Verify that the service method was called
	        verify(saleService, times(1)).countTotalQuantitiesByCategory();
	    }

	//Positive Test Cases
	@Test
	public void testGetQuantityByCategoryForYearDto() throws Exception {
		// Mock the service response
		QuantityByCategoryForYearDto byCategoryForYearDto = new QuantityByCategoryForYearDto() {

			@Override
			public Long getSalesCount() {
				// TODO Auto-generated method stub
				return 50l;
			}

			@Override
			public String getProdCategory() {
				// TODO Auto-generated method stub
				return "Category1";
			}

			@Override
			public String getCalendarYear() {
				// TODO Auto-generated method stub
				return "2022";
			}
		};
		List<QuantityByCategoryForYearDto> quantitiesByCategoryForYear = Arrays.asList(byCategoryForYearDto);
		when(saleService.countTotalQuantitiesByCategoryForYear(2022)).thenReturn(quantitiesByCategoryForYear);

		// Perform the GET request
		mockMvc.perform(get("/sales/qtys/categorywise/year/{year}", 2022)
				.with(user("username").password("password").roles("ADMIN"))).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].prodCategory", is("Category1")))
				.andExpect(jsonPath("$[0].calendarYear", is("2022"))).andExpect(jsonPath("$[0].salesCount", is(50)));

	}

	//Negative Test Cases
	@Test
	public void testGetQuantityByCategoryForYearDto_QuantitiesNotFound() throws Exception {
		// Mock the service response to throw an exception
		Integer year = 2022;
		when(saleService.countTotalQuantitiesByCategoryForYear(year))
				.thenThrow(new SalesException("No Category Count is Present for year " + year));

		// Perform the GET request and validate the response
		mockMvc.perform(get("/sales/qtys/categorywise/year/{year}", year)
				.with(user("username").password("password").roles("ADMIN"))).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.errorMessage").value("No Category Count is Present for year " + year));

		// Verify that the service method was called
		verify(saleService, times(1)).countTotalQuantitiesByCategoryForYear(year);
	}

	//Positive Test Cases
	@Test
	public void testGetTotalQuantitiesSoldByCategory() throws Exception {
		// Mock the service response

		QuantitySoldByCategoryDto quantitySoldByCategoryDto = new QuantitySoldByCategoryDto() {

			@Override
			public String getProdCategory() {
				// TODO Auto-generated method stub
				return "Category1";
			}

			@Override
			public Double getAmountSold() {
				// TODO Auto-generated method stub
				return 100.0;
			}
		};
		List<QuantitySoldByCategoryDto> quantitiesSoldByCategory = Arrays.asList(quantitySoldByCategoryDto);
		when(saleService.countTotalQuantitiesSoldByCategory()).thenReturn(quantitiesSoldByCategory);

		// Perform the GET request
		mockMvc.perform(get("/sales/sold/categorywise").with(user("username").password("password").roles("ADMIN")))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].prodCategory", is("Category1")))
				.andExpect(jsonPath("$[0].amountSold", is(100.0)));
	}

	@Test
    public void testGetTotalQuantitiesSoldByCategory_QuantitiesNotFound() throws Exception {
        // Mock the service response to throw an exception
        when(saleService.countTotalQuantitiesSoldByCategory())
                .thenThrow(new SalesException("No Count is Present for Quantity"));

        // Perform the GET request and validate the response
        mockMvc.perform(get("/sales/sold/categorywise").with(user("username").password("password").roles("ADMIN")))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value("No Count is Present for Quantity"));

        // Verify that the service method was called
        verify(saleService, times(1)).countTotalQuantitiesSoldByCategory();
    }

	//Positive Test Cases
	@Test
	public void testGetTotalQuantitiesSoldByCategoryForYear() throws Exception {
		// Mock the service response
		QuantityBySoldCategoryForYearDto bySoldCategoryForYearDto = new QuantityBySoldCategoryForYearDto() {

			@Override
			public String getProdCategory() {
				// TODO Auto-generated method stub
				return "Category1";
			}

			@Override
			public String getCalendarYear() {
				// TODO Auto-generated method stub
				return "2022";
			}

			@Override
			public Double getAmountSold() {
				// TODO Auto-generated method stub
				return 500.0;
			}
		};
		List<QuantityBySoldCategoryForYearDto> quantitiesSoldByCategoryForYear = Arrays
				.asList(bySoldCategoryForYearDto);
		when(saleService.TotalAmountSoldByCategoryForYear(2022)).thenReturn(quantitiesSoldByCategoryForYear);

		// Perform the GET request
		mockMvc.perform(get("/sales/sold/categorywise/year/{year}", 2022)
				.with(user("username").password("password").roles("ADMIN"))).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].prodCategory", is("Category1")))
				.andExpect(jsonPath("$[0].calendarYear", is("2022"))).andExpect(jsonPath("$[0].amountSold", is(500.0)));
//	               
	}

	@Test
	public void testGetTotalQuantitiesSoldByCategoryForYear_QuantitiesNotFound() throws Exception {
		// Mock the service response to throw an exception
		Integer year = 2022;
		when(saleService.TotalAmountSoldByCategoryForYear(year))
				.thenThrow(new SalesException("No quantity amount Present for year " + year));

		// Perform the GET request and validate the response
		mockMvc.perform(get("/sales/sold/categorywise/year/{year}", year)
				.with(user("username").password("password").roles("ADMIN"))).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.errorMessage").value("No quantity amount Present for year " + year));

		
		verify(saleService, times(1)).TotalAmountSoldByCategoryForYear(year);
	}
}
