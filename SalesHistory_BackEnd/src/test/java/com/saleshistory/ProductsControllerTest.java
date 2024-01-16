package com.saleshistory;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saleshistory.dto.ChannelSoldProductDTO;
import com.saleshistory.dto.DuplicateProductDTO;
import com.saleshistory.dto.ProductsDto;
import com.saleshistory.dto.ResponseDto;
import com.saleshistory.entity.Products;
import com.saleshistory.entity.Supplier;
import com.saleshistory.exceptions.ProductsException;
import com.saleshistory.repository.ProductsRepository;
import com.saleshistory.service.ProductsService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductsControllerTest {

	@BeforeEach
    public void setupAuthentication() {
        Authentication auth = new UsernamePasswordAuthenticationToken("omjawade", "omjawade",
                Collections.singletonList(new SimpleGrantedAuthority("Admin")));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductsService prodService;
	
	@MockBean
	private ProductsRepository productRepo;
	
	

	@Test
	public void testGetAllProducts() throws Exception {
		ProductsDto productsDto = new ProductsDto();

		List<ProductsDto> productDtoList = Arrays.asList(productsDto);

		when(prodService.getAllProducts()).thenReturn(productDtoList);

		mockMvc.perform(MockMvcRequestBuilders.get("/products")).andExpect(status().isOk());
		verify(prodService, times(1)).getAllProducts();
	}
	
	@Test
	public void testGetAllProducts_NoProductsFound() throws Exception {
	    when(prodService.getAllProducts()).thenThrow(new ProductsException("List is Empty."));

	    mockMvc.perform(MockMvcRequestBuilders.get("/products"))
	            .andExpect(status().isNotFound())
	            .andExpect(jsonPath("$.errorMessage").value("List is Empty."));

	    verify(prodService, times(1)).getAllProducts();
	}


	@Test
	public void testGetProductByProdCategory() throws Exception {
		String prodName = "Anne";
		ProductsDto productsDto = new ProductsDto();
		List<ProductsDto> productsDtosList = Arrays.asList(productsDto);
		productsDto.setProdName(prodName);
		when(prodService.getProductByProdCategory(prodName)).thenReturn(productsDtosList);

		mockMvc.perform(get("/products/productscategory/{prodName}", prodName)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].prodName").value("Anne"));

		verify(prodService, times(1)).getProductByProdCategory(prodName);

	}
	
	@Test
	public void testGetProductByProdCategory_NoProductsFound() throws Exception {
	    String prodName = "Non-existing Category";

	    when(prodService.getProductByProdCategory(prodName))
	            .thenThrow(new ProductsException("Products are not present for category " + prodName));

	    mockMvc.perform(get("/products/productscategory/{prodName}", prodName))
	            .andExpect(status().isNotFound())
	            .andExpect(jsonPath("$.errorMessage").value("Products are not present for category " + prodName));

	    verify(prodService, times(1)).getProductByProdCategory(prodName);
	}


	@Test
	@WithMockUser(username="omjawade",roles="Admin")
	public void testSaveProduct() throws Exception {
	    
	    ProductsDto requestDto = new ProductsDto();
	    // Set the required fields and other necessary fields in the requestDto
	    requestDto.setProdId(1);
	    requestDto.setProdName("abcd");
	    requestDto.setProdSubcategoryId(2);
	    requestDto.setProdCategoryId(1);
	    requestDto.setProdWeightClass(225);
	    requestDto.setSupplierId(2);
	    requestDto.setProdTotalId(2);
	    requestDto.setProdSrcId(2);
	    requestDto.setProdTotalId(1);
	    // Mock the service method to return a success response
	    ResponseDto expectedResponse = new ResponseDto();
	    expectedResponse.setResponseMessage("Product added successfully");
	    when(prodService.addProduct(any(ProductsDto.class))).thenReturn(expectedResponse);

	    // Perform the POST request to the controller
	    mockMvc.perform(MockMvcRequestBuilders.post("/products")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(new ObjectMapper().writeValueAsString(requestDto)))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.responseMessage").value(expectedResponse.getResponseMessage()));

	    verify(prodService, times(1)).addProduct(any(ProductsDto.class));
	}

	
	@Test
	@WithMockUser(username="omjawade",roles="Admin")
	public void testSaveProduct_InvalidRequest() throws Exception {
	    ProductsDto requestDto = new ProductsDto();
	    
	    requestDto.setProdId(null);

	    mockMvc.perform(post("/products")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(new ObjectMapper().writeValueAsString(requestDto)))
	            .andExpect(status().isBadRequest());

	    verify(prodService, times(0)).addProduct(any(ProductsDto.class));
	}



	@Test
	public void testUpdateProduct() throws Exception {
		
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResponseMessage("Product updated successfully");
		when(prodService.updateProduct(any(ProductsDto.class))).thenReturn(responseDto);

		
		mockMvc.perform(put("/products").contentType(MediaType.APPLICATION_JSON).content(
				"{\"prodId\":1,\"prodName\":\"Product1\",\"prodDesc\":\"Description1\",\"prodSubcategory\":\"Subcategory1\",\"prodSubcategoryId\":1,\"prodSubcategoryDesc\":\"SubcategoryDesc1\",\"prodCategory\":\"Category1\",\"prodCategoryId\":1,\"prodCategoryDesc\":\"CategoryDesc1\",\"prodWeightClass\":1,\"prodUnitOfMeasure\":\"Unit1\",\"prodPackSize\":\"PackSize1\",\"supplierId\":1,\"prodStatus\":\"Status1\",\"prodListPrice\":10.0,\"prodMinPrice\":5.0,\"prodTotal\":\"Total1\",\"prodTotalId\":1,\"prodSrcId\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.responseMessage").value("Product updated successfully"));
	}
	
	@Test
	public void testUpdateProduct_InvalidInput() throws Exception {
		
		ResponseDto responseDto = new ResponseDto();
		ProductsDto productsDto=new ProductsDto();
		productsDto.setProdId(1);
		
		when(prodService.updateProduct(any(ProductsDto.class))).thenReturn(responseDto);

		
		mockMvc.perform(put("/products").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
	
	


	@Test
	public void testGetProductsById() throws Exception {
		Integer prodId = 1;
		ProductsDto productsDto = new ProductsDto();
		productsDto.setProdId(prodId);

		when(prodService.getProductsById(prodId)).thenReturn(productsDto);

		mockMvc.perform(get("/products/{prodId}", prodId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.prodId", is(prodId)));

		verify(prodService, times(1)).getProductsById(prodId);
	}
	
	@Test
	public void testGetProductsById_ProductNotFound() throws Exception {
	    Integer prodId = 1;

	    when(prodService.getProductsById(prodId))
	            .thenThrow(new ProductsException("Product not present for id " + prodId));

	    mockMvc.perform(get("/products/{prodId}", prodId))
	            .andExpect(status().isNotFound())
	            .andExpect(jsonPath("$.errorMessage").value("Product not present for id " + prodId));

	    verify(prodService, times(1)).getProductsById(prodId);
	}

	@Test
	public void testGetProductBySubProdcategory() throws Exception {
		String prodSubcategory = "Cricket";
		ProductsDto productsDto = new ProductsDto();
		productsDto.setProdSubcategory(prodSubcategory);
		List<ProductsDto> prodList = Arrays.asList(productsDto);

		when(prodService.getProductByProdSubcategory(prodSubcategory)).thenReturn(prodList);
		mockMvc.perform(get("/products/productssubcategory/{prodSubcategory}", prodSubcategory))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].prodSubcategory").value(prodSubcategory));

		verify(prodService, times(1)).getProductByProdSubcategory(prodSubcategory);
	}
	
	@Test
	public void testGetProductBySubProdcategory_NoProductsFound() throws Exception {
	    String prodSubcategory = "Non-existing Subcategory";

	    when(prodService.getProductByProdSubcategory(prodSubcategory))
	            .thenThrow(new ProductsException("Products not present for sub-category " + prodSubcategory));

	    mockMvc.perform(get("/products/productssubcategory/{prodSubcategory}", prodSubcategory))
	            .andExpect(status().isNotFound())
	            .andExpect(jsonPath("$.errorMessage").value("Products not present for sub-category " + prodSubcategory));

	    verify(prodService, times(1)).getProductByProdSubcategory(prodSubcategory);
	}

	@Test
	public void testSearchProductsBySupplier() throws Exception {
		Integer supplierId = 1;
		ProductsDto productsDto = new ProductsDto();
		productsDto.setSupplierId(supplierId);
		List<ProductsDto> prodList = Arrays.asList(productsDto);

		when(prodService.getProductBySupplierId(supplierId)).thenReturn(prodList);
		mockMvc.perform(get("/products/productssupplier/{supplierId}", supplierId)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].supplierId").value(supplierId));

		verify(prodService, times(1)).getProductBySupplierId(supplierId);
	}

	@Test
	public void testSearchProductsBySupplier_NoProductsFound() throws Exception {
	    Integer supplierId = 1;

	    when(prodService.getProductBySupplierId(supplierId))
	            .thenThrow(new ProductsException("Products not present for supplier id " + supplierId));

	    mockMvc.perform(get("/products/productssupplier/{supplierId}", supplierId))
	            .andExpect(status().isNotFound())
	            .andExpect(jsonPath("$.errorMessage").value("Products not present for supplier id " + supplierId));

	    verify(prodService, times(1)).getProductBySupplierId(supplierId);
	}
	
	@Test
	public void testGetDuplicateProducts() throws Exception {

		DuplicateProductDTO duplicateProductDTO = new DuplicateProductDTO() {

			@Override
			public String getProdName() {
				return null;
			}

			@Override
			public Integer getCount() {
				return null;
			}
		};
		List<DuplicateProductDTO> productDtoList = Arrays.asList(duplicateProductDTO);

		when(prodService.findDuplicateProducts()).thenReturn(productDtoList);

		mockMvc.perform(MockMvcRequestBuilders.get("/products/duplicates")).andExpect(status().isOk());
		verify(prodService, times(1)).findDuplicateProducts();
	}

	@Test
	public void testGetDuplicateProducts_NoDuplicatesFound() throws Exception {
	    when(prodService.findDuplicateProducts())
	            .thenThrow(new ProductsException("Duplicate products are not present"));

	    mockMvc.perform(MockMvcRequestBuilders.get("/products/duplicates"))
	            .andExpect(status().isNotFound())
	            .andExpect(jsonPath("$.errorMessage").value("Duplicate products are not present"));

	    verify(prodService, times(1)).findDuplicateProducts();
	}

	
	@Test
	public void testGetProductsSoldByChannel() throws Exception {
		// Mock data
		String prodDesc = "Cricket Bag";
		ProductsDto dto = new ProductsDto();
		ChannelSoldProductDTO channelSoldProductDTO = new ChannelSoldProductDTO() {

			@Override
			public String getProdName() {
				// TODO Auto-generated method stub
				return "Bat";
			}

			@Override
			public Integer getProdListPrice() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Integer getProdId() {
				// TODO Auto-generated method stub
				return 1;
			}

			@Override
			public String getProdDesc() {
				// TODO Auto-generated method stub
				return "Cricket Bag";
			}

			@Override
			public String getProdCategory() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getCustLastName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Integer getCustId() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getCustFirstName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getChannelDesc() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		dto.setProdDesc(prodDesc);
		List<ChannelSoldProductDTO> channelSoldProducts = Arrays.asList(channelSoldProductDTO);
		when(prodService.findSoldProductsByChannel("retail")).thenReturn(channelSoldProducts);
		mockMvc.perform(get("/products/soldbychannel").param("field", "retail")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].prodName", is("Bat"))).andExpect(jsonPath("$[0].prodId", is(1)))
				.andExpect(jsonPath("$[0].prodDesc", is("Cricket Bag")));
		verify(prodService, times(1)).findSoldProductsByChannel("retail");
	}
	
	@Test
	public void testGetProductsSoldByChannel_NoProductsFound() throws Exception {
	    String channelName = "Non-existing Channel";

	    when(prodService.findSoldProductsByChannel(channelName))
	            .thenThrow(new ProductsException("Products Not Found for channel name " + channelName));

	    mockMvc.perform(get("/products/soldbychannel").param("field", channelName))
	            .andExpect(status().isNotFound())
	            .andExpect(jsonPath("$.errorMessage").value("Products Not Found for channel name " + channelName));

	    verify(prodService, times(1)).findSoldProductsByChannel(channelName);
	}


	@Test
	public void testGetProductsOrderByField() throws Exception {
		ProductsDto product1 = new ProductsDto();
		product1.setProdId(1);
		product1.setProdName("Product 1");
		product1.setProdDesc("Description 1");

		ProductsDto product2 = new ProductsDto();
		product2.setProdId(2);
		product2.setProdName("Product 2");
		product2.setProdDesc("Description 2");

		List<ProductsDto> products = Arrays.asList(product1, product2);

		
		when(prodService.getProductsOrderByField("prodSubcategory")).thenReturn(products);

		
		mockMvc.perform(get("/products/sort").param("field", "prodSubcategory")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].prodId", is(1))).andExpect(jsonPath("$[0].prodName", is("Product 1")))
				.andExpect(jsonPath("$[0].prodDesc", is("Description 1"))).andExpect(jsonPath("$[1].prodId", is(2)))
				.andExpect(jsonPath("$[1].prodName", is("Product 2")))
				.andExpect(jsonPath("$[1].prodDesc", is("Description 2")));

		
		verify(prodService, times(1)).getProductsOrderByField("prodSubcategory");

	}
	
	@Test
	public void testGetProductsOrderByField_NoProductsFound() throws Exception {
	    String field = "Non-existing Field";

	    when(prodService.getProductsOrderByField(field))
	            .thenThrow(new ProductsException("Products not found for fields " + field));

	    mockMvc.perform(get("/products/sort").param("field", field))
	            .andExpect(status().isNotFound())
	            .andExpect(jsonPath("$.errorMessage").value("Products not found for fields " + field));

	    verify(prodService, times(1)).getProductsOrderByField(field);
	}

	@Test
	public void testFindByProdStatusSold() throws Exception {
		String prodName = "Sonic Core Graphite Racquet";
		String prodDesc = "Sonic Core Graphite Racquet";
		ProductsDto productsDto = new ProductsDto();
		productsDto.setProdName(prodName);
		productsDto.setProdDesc(prodDesc);

		List<ProductsDto> productsList = Arrays.asList(productsDto);
		when(prodService.findByProdStatusSold()).thenReturn(productsList);

		mockMvc.perform(get("/products/status/sold")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].prodName").value(prodName))
				.andExpect(jsonPath("$[0].prodDesc").value(prodDesc));
		verify(prodService, times(1)).findByProdStatusSold();
	}

	@Test
	public void testFindByProdStatusSold_NoProductsFound() throws Exception {
	    when(prodService.findByProdStatusSold())
	            .thenThrow(new ProductsException("No Products with status 'Sold' found"));

	    mockMvc.perform(get("/products/status/sold"))
	            .andExpect(status().isNotFound())
	            .andExpect(jsonPath("$.errorMessage").value("No Products with status 'Sold' found"));

	    verify(prodService, times(1)).findByProdStatusSold();
	}
	
	@Test
	public void testGetProductsByProdStatus() throws Exception {
		Integer prodId = 13;
		Integer prodSubcategoryId = 2044;
		ProductsDto productsDto = new ProductsDto();
		productsDto.setProdId(prodId);
		productsDto.setProdSubcategoryId(prodSubcategoryId);

		List<ProductsDto> productsDtosList = Arrays.asList(productsDto);
		when(prodService.getProductByProdStatus("status")).thenReturn(productsDtosList);

		mockMvc.perform(get("/products/productsstatus/status")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].prodId").value(prodId))
				.andExpect(jsonPath("$[0].prodSubcategoryId").value(prodSubcategoryId));
		verify(prodService, times(1)).getProductByProdStatus("status");
	}
	
	@Test
	public void testGetProductsByProdStatus_NoProductsFound() throws Exception {
	    String prodStatus = "Status";

	    when(prodService.getProductByProdStatus(prodStatus))
	            .thenThrow(new ProductsException("Products are not present for status " + prodStatus));

	    mockMvc.perform(get("/products/productsstatus/{prodStatus}", prodStatus))
	            .andExpect(status().isNotFound())
	            .andExpect(jsonPath("$.errorMessage").value("Products are not present for status " + prodStatus));

	    verify(prodService, times(1)).getProductByProdStatus(prodStatus);
	}

}
