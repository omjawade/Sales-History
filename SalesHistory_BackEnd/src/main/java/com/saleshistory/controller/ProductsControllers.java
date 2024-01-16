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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saleshistory.dto.ChannelSoldProductDTO;
import com.saleshistory.dto.DuplicateProductDTO;
import com.saleshistory.dto.ProductsDto;
import com.saleshistory.dto.ResponseDto;
import com.saleshistory.service.ProductsService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/products")
public class ProductsControllers {

	@Autowired
	private ProductsService prodService;

	/**
     * Get all products.
     *
     * @return ResponseEntity with a list of product DTOs
     */
	@GetMapping
	public ResponseEntity<List<ProductsDto>> getAllProducts() {
		List<ProductsDto> allProducts = prodService.getAllProducts();
		return new ResponseEntity<List<ProductsDto>>(allProducts, HttpStatus.OK);
	}

	/**
     * Get a product by ID.
     *
     * @param prodId the ID of the product
     * @return ResponseEntity with the product DTO
     */
	@GetMapping("/{prodId}") // dynamic value
	public ResponseEntity<ProductsDto> getProductsById(@PathVariable Integer prodId) {
		ProductsDto allProducts = prodService.getProductsById(prodId);
		return new ResponseEntity<ProductsDto>(allProducts, HttpStatus.OK);
	}

	/**
     * Save a new product.
     *
     * @param productsDto the DTO containing product information
     * @return ResponseEntity with the response DTO
     */
	@PostMapping
	public ResponseEntity<ResponseDto> saveProduct( @Valid @RequestBody ProductsDto productsDto) {
		ResponseDto productDto = prodService.addProduct(productsDto);
		return new ResponseEntity<ResponseDto>(productDto, HttpStatus.OK);
	}

	/**
     * Get products by category.
     *
     * @param prodCategory the category of the products
     * @return ResponseEntity with a list of product DTOs
     */
	@GetMapping("/productscategory/{prodCategory}")
	public ResponseEntity<List<ProductsDto>> getProductByProdCategory(@Valid @PathVariable String prodCategory) {
		List<ProductsDto> productsDto = prodService.getProductByProdCategory(prodCategory);
		return new ResponseEntity<List<ProductsDto>>(productsDto, HttpStatus.OK);
	}

	/**
     * Get products by status.
     *
     * @param prodStatus the status of the products
     * @return ResponseEntity with a list of product DTOs
     */
	@GetMapping("/productsstatus/{prodStatus}")
	public ResponseEntity<List<ProductsDto>> getProductsByProdStatus(@Valid @PathVariable String prodStatus) {
		List<ProductsDto> productsDto = prodService.getProductByProdStatus(prodStatus);
		return new ResponseEntity<List<ProductsDto>>(productsDto, HttpStatus.OK);
	}

	 /**
     * Update an existing product.
     *
     * @param productDto the DTO containing updated product information
     * @return ResponseEntity with the response DTO
     */
	@PutMapping
	public ResponseEntity<ResponseDto> updateProduct(@Valid @RequestBody ProductsDto productDto) {
		ResponseDto prodDto = prodService.updateProduct(productDto);
		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}

	/**
     * Get products by subcategory.
     *
     * @param prodSubcategory the subcategory of the products
     * @return ResponseEntity with a list of product DTOs
     */
	@GetMapping("/productssubcategory/{prodSubcategory}")
	public ResponseEntity<List<ProductsDto>> getProductBySubProdcategory(@Valid @PathVariable String prodSubcategory) {
		List<ProductsDto> productsDto = prodService.getProductByProdSubcategory(prodSubcategory);
		return new ResponseEntity<List<ProductsDto>>(productsDto, HttpStatus.OK);
	}

	/**
     * Search products by supplier.
     *
     * @param supplierId the ID of the supplier
     * @return ResponseEntity with a list of product DTOs
     */
	@GetMapping("/productssupplier/{supplierId}")
	public ResponseEntity<List<ProductsDto>> searchProductsBySupplier(@PathVariable Integer supplierId) {
		List<ProductsDto> productsDtoList = prodService.getProductBySupplierId(supplierId);
		return new ResponseEntity<List<ProductsDto>>(productsDtoList, HttpStatus.OK);
	}

	/**
     * Get duplicate products.
     *
     * @return ResponseEntity with a list of duplicate product DTOs
     */
	@GetMapping("/duplicates")
	public ResponseEntity<List<DuplicateProductDTO>> getDuplicateProducts() {
		List<DuplicateProductDTO> duplicateProducts = prodService.findDuplicateProducts();

		return ResponseEntity.ok(duplicateProducts);

	}

	/**
     * Get products sold by channel.
     *
     * @param channel the channel name
     * @return ResponseEntity with a list of channel sold product DTOs
     */
	@GetMapping("/soldbychannel")
	public ResponseEntity<List<ChannelSoldProductDTO>> getProductsSoldByChannel(@RequestParam("field") String channel) {
		List<ChannelSoldProductDTO> channelSoldProductDTOs = prodService.findSoldProductsByChannel(channel);

		return ResponseEntity.ok(channelSoldProductDTOs);

	}

	/**
     * Get products ordered by a specific field.
     *
     * @param field the field to order the products by
     * @return ResponseEntity with a list of ordered product DTOs
     */
	@GetMapping("/sort")
	public ResponseEntity<List<ProductsDto>> getProductsOrderByField(@RequestParam("field") String field) {
		List<ProductsDto> products = prodService.getProductsOrderByField(field);
		return ResponseEntity.ok(products);
	}

	/**
     * Get products with a sold status.
     *
     * @return ResponseEntity with a list of product DTOs with a sold status
     */
	@GetMapping("/status/sold")
	public ResponseEntity<List<ProductsDto>> findByProdStatusSold() {
		List<ProductsDto> sold = prodService.findByProdStatusSold();
		return new ResponseEntity<List<ProductsDto>>(sold, HttpStatus.OK);

	}

}
