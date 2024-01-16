package com.saleshistory;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.saleshistory.dto.ProductsDto;
import com.saleshistory.exceptions.ChannelsException;
import com.saleshistory.service.ChannelsService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ChannelsControllersTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ChannelsService channelsService;
	
	//Positive Test Case
	@Test
	public void testGetRevenueByChannelId() throws Exception {
	   
	    Integer channelId = 1;
	    Double revenue = 5000.0;
	    when(channelsService.getRevenueByChannel(channelId)).thenReturn(revenue);

	    
	    mockMvc.perform(get("/channels/{channelId}/revenue", channelId).with(user("username").password("password").roles("ADMIN")))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$").value(revenue));
	}
	
	//Negative Test Case
	@Test
    public void testGetRevenueByChannelId_ChannelNotFound() throws Exception {
        Integer channelId = 1;
        when(channelsService.getRevenueByChannel(channelId))
                .thenThrow(new ChannelsException("Channel not found."));

        mockMvc.perform(get("/channels/{channelId}/revenue", channelId).with(user("username").password("password").roles("ADMIN")))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value("Channel not found."));

        verify(channelsService, times(1)).getRevenueByChannel(channelId);
    }
	
	//Positive Test Case
	@Test
    public void testGetAggregateSaleByChannel() throws Exception {
		Integer channelId=1;
        Integer aggregateSale = 100;
        when(channelsService.getAggregateSaleByChannel(1)).thenReturn(aggregateSale);

        mockMvc.perform(get("/channels/{channelId}/aggregatesale",channelId).with(user("username").password("password").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(aggregateSale));
    }
	
	//Negative Test Case
	@Test
    public void testGetAggregateSaleByChannel_ChannelNotFound() throws Exception {
        Integer channelId = 1;
        when(channelsService.getAggregateSaleByChannel(channelId))
                .thenThrow(new ChannelsException("Channel not found."));

        mockMvc.perform(get("/channels/{channelId}/aggregatesale", channelId).with(user("username").password("password").roles("ADMIN")))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value("Channel not found."));

        verify(channelsService, times(1)).getAggregateSaleByChannel(channelId);
    }

	//Positive Test Case
    @Test
    public void testGetMostSoldProductsByChannel() throws Exception {
    	Integer channelId =1;
        List<ProductsDto> productsDtoList = new ArrayList<>();
        ProductsDto product1 = new ProductsDto();
        product1.setProdId(1);
        product1.setProdName("Product 1");
        // Set other properties for product1
        ProductsDto product2 = new ProductsDto();
        product2.setProdId(2);
        product2.setProdName("Product 2");
        // Set other properties for product2
        productsDtoList.add(product1);
        productsDtoList.add(product2);
        when(channelsService.getMostSoldProductsByChannel(1)).thenReturn(productsDtoList);

        mockMvc.perform(get("/channels/{channelId}/mostSoldProducts",channelId).with(user("username").password("password").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(productsDtoList.size()))
                .andExpect(jsonPath("$[0].prodId").value(product1.getProdId()))
                .andExpect(jsonPath("$[0].prodName").value(product1.getProdName()))
                // Assert other properties of product1
                .andExpect(jsonPath("$[1].prodId").value(product2.getProdId()))
                .andExpect(jsonPath("$[1].prodName").value(product2.getProdName()));
    }
    
  //Negative Test Case
    @Test
    public void testGetMostSoldProductsByChannel_ChannelNotFound() throws Exception {
        Integer channelId = 1;
        when(channelsService.getMostSoldProductsByChannel(channelId))
                .thenThrow(new ChannelsException("Channel not found."));

        mockMvc.perform(get("/channels/{channelId}/mostSoldProducts", channelId).with(user("username").password("password").roles("ADMIN")))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value("Channel not found."));

        verify(channelsService, times(1)).getMostSoldProductsByChannel(channelId);
    }
}
