package com.saleshistory.service;

import java.util.List;

import com.saleshistory.dto.ChannelSoldProductDTO;
import com.saleshistory.dto.DuplicateProductDTO;
import com.saleshistory.dto.ProductsDto;
import com.saleshistory.dto.ResponseDto;

public interface ProductsService {

	public List<ProductsDto> getAllProducts();

	public List<ProductsDto> getProductByProdCategory(String prodCategory);

	public List<ProductsDto> getProductByProdSubcategory(String prodSubcategory);

	public List<ProductsDto> getProductByProdStatus(String prodStatus);

	public ResponseDto updateProduct(ProductsDto productsDto);

	public ResponseDto addProduct(ProductsDto productsDto);

	public List<ProductsDto> getProductBySupplierId(Integer supplierId);

	public ProductsDto getProductsById(Integer Id);

	public List<DuplicateProductDTO> findDuplicateProducts();

	public List<ChannelSoldProductDTO> findSoldProductsByChannel(String channelName);

	List<ProductsDto> getProductsOrderByField(String field);

	List<ProductsDto> findByProdStatusSold();

}
