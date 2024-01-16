package com.saleshistory.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.saleshistory.dto.ChannelSoldProductDTO;
import com.saleshistory.dto.CustomersDto;
import com.saleshistory.dto.DuplicateProductDTO;
import com.saleshistory.dto.ProductsDto;
import com.saleshistory.dto.ResponseDto;
import com.saleshistory.entity.Customers;
import com.saleshistory.entity.Products;
import com.saleshistory.exceptions.ProductsException;
import com.saleshistory.repository.ProductsRepository;
import com.saleshistory.repository.SuppliersRepository;
import com.saleshistory.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductsRepository productRepo;

	@Autowired
	SuppliersRepository supplierRepo;

	ResponseDto responseDto = new ResponseDto();

	@Override
	public List<ProductsDto> getAllProducts() {
		List<Products> productsList = productRepo.findAll();
		if (!productsList.isEmpty()) {
			List<ProductsDto> productDtoList = new ArrayList<>();
			for (Products product : productsList) {
				ProductsDto dto = new ProductsDto();
				dto.setSupplierId(product.getSupplierId().getId());
				BeanUtils.copyProperties(product, dto);
				productDtoList.add(dto);
			}
			return productDtoList;
		}
		throw new ProductsException("List is Empty.");
	}

	@Override
	public List<ProductsDto> getProductByProdCategory(String prodCategory) {
		List<Products> productsList = productRepo.findByProdCategory(prodCategory);
		if (!productsList.isEmpty()) {
			List<ProductsDto> productDtoList = new ArrayList<>();
			for (Products product : productsList) {
				ProductsDto dto = new ProductsDto();
				dto.setSupplierId(product.getSupplierId().getId());
				BeanUtils.copyProperties(product, dto);
				productDtoList.add(dto);
			}
			return productDtoList;
		}
		throw new ProductsException("Products are not present for category " + prodCategory);
	}

	@Override
	public List<ProductsDto> getProductByProdStatus(String prodStatus) {
		List<Products> productsList = productRepo.findByProdStatus(prodStatus);
		if (!productsList.isEmpty()) {
			List<ProductsDto> productDtoList = new ArrayList<>();
			for (Products product : productsList) {
				ProductsDto dto = new ProductsDto();
				dto.setSupplierId(product.getSupplierId().getId());
				BeanUtils.copyProperties(product, dto);
				productDtoList.add(dto);
			}
			return productDtoList;
		}
		throw new ProductsException("Products are not present for status " + prodStatus);
	}

	@Override
	public ResponseDto addProduct(ProductsDto productsDto) {
//		Optional<Products> findById = productRepo.findById(productsDto.getProdId());
//		if (!findById.isPresent()) {

			Products product = new Products();
			product.setSupplierId(supplierRepo.findById(productsDto.getSupplierId()).get());
			BeanUtils.copyProperties(productsDto, product);
			productRepo.save(product);
			responseDto.setResponseMessage("Product added successfully");
			return responseDto;
//		}
//		throw new ProductsException("Product with Id " + productsDto.getProdId() + " is already Present");

	}

	@Override
	public ResponseDto updateProduct(ProductsDto productsDto) {

		Optional<Products> findById = productRepo.findById(productsDto.getProdId());
		if (findById.isPresent()) {
			ProductsDto productsDto2 = getProductsById(productsDto.getProdId());
			Products prod = new Products();
			prod.setSupplierId(supplierRepo.findById(productsDto.getSupplierId()).get());
			BeanUtils.copyProperties(productsDto, prod);
			productRepo.save(prod);
			responseDto.setResponseMessage("Product details updated successfully.");
			return responseDto;
		}
		throw new ProductsException("Products with Id " + productsDto.getProdId() + " is not present");
	}

	@Override
	public ProductsDto getProductsById(Integer Id) {
		Optional<Products> findById = productRepo.findById(Id);
		if (findById.isPresent()) {
			ProductsDto dto = new ProductsDto();
			Products prod = new Products();
			BeanUtils.copyProperties(findById.get(), dto);
			dto.setSupplierId(findById.get().getSupplierId().getId());
			return dto;
		}
		throw new ProductsException("Product not present for id " + Id);
	}

	@Override
	public List<ProductsDto> getProductByProdSubcategory(String prodSubcategory) {
		List<Products> productsList = productRepo.findByProdSubcategory(prodSubcategory);
		if (!productsList.isEmpty()) {
			List<ProductsDto> productDtoList = new ArrayList<>();
			for (Products product : productsList) {
				ProductsDto dto = new ProductsDto();
				dto.setSupplierId(product.getSupplierId().getId());
				BeanUtils.copyProperties(product, dto);
				productDtoList.add(dto);
			}
			return productDtoList;
		}
		throw new ProductsException("Products not present for sub-category " + prodSubcategory);
	}

	@Override
	public List<ProductsDto> getProductBySupplierId(Integer supplierId) {
		List<Products> productsList = productRepo.findBySupplierId_Id(supplierId);
		if (!productsList.isEmpty()) {
			List<ProductsDto> productDtoList = new ArrayList<>();
			for (Products product : productsList) {
				ProductsDto dto = setProductDto(product);
				dto.setSupplierId(product.getSupplierId().getId());

				productDtoList.add(dto);
			}
			return productDtoList;
		}
		throw new ProductsException("Products not present for supplier id " + supplierId);
	}

	public ProductsDto setProductDto(Products product) {
		ProductsDto dto = new ProductsDto();

		dto.setProdId(product.getProdId());
		dto.setProdName(product.getProdName());
		dto.setProdDesc(product.getProdDesc());
		dto.setProdSubcategory(product.getProdSubcategory());
		dto.setProdCategory(product.getProdCategory());
		dto.setProdCategoryId(product.getProdCategoryId());
		dto.setProdEffFrom(product.getProdEffFrom());
		dto.setProdEffTo(product.getProdEffTo());
		dto.setProdListPrice(product.getProdListPrice());
		dto.setProdMinPrice(product.getProdMinPrice());
		dto.setProdStatus(product.getProdStatus());
		dto.setProdTotal(product.getProdTotal());
		dto.setProdTotalId(product.getProdTotalId());
		dto.setProdWeightClass(product.getProdWeightClass());
		dto.setProdValid(product.getProdValid());
		dto.setProdUnitOfMeasure(product.getProdUnitOfMeasure());
		dto.setProdSubcategoryDesc(product.getProdSubcategoryDesc());
		dto.setProdCategoryDesc(product.getProdCategoryDesc());
		dto.setProdPackSize(product.getProdPackSize());
		dto.setProdSubcategoryId(product.getProdSubcategoryId());
		dto.setProdSrcId(product.getProdSrcId());
		return dto;
	}

	@Override
	public List<DuplicateProductDTO> findDuplicateProducts() {
		List<DuplicateProductDTO> duplicateProductDTOs = productRepo.findDuplicateProducts();
		if (!duplicateProductDTOs.isEmpty()) {
			return productRepo.findDuplicateProducts();
		}
		throw new ProductsException("Duplicates products are not present");
	}

	@Override
	public List<ChannelSoldProductDTO> findSoldProductsByChannel(String channelName) {
		List<ChannelSoldProductDTO> channelSoldProductDTOs = productRepo.findSoldProductsByChannel(channelName);
		if (!channelSoldProductDTOs.isEmpty()) {
			return productRepo.findSoldProductsByChannel(channelName);
		}
		throw new ProductsException("Products Not Found for channel name " + channelName);
	}

	@Override
	public List<ProductsDto> getProductsOrderByField(String field) {
		List<Products> productList = productRepo.findAll(Sort.by(field));
		if (!productList.isEmpty()) {
			List<ProductsDto> productsDtos = new ArrayList<>();
			for (Products p : productList) {
				ProductsDto productsDto = new ProductsDto();
				BeanUtils.copyProperties(p, productsDto);
				productsDtos.add(productsDto);
			}
			return productsDtos;
		}
		throw new ProductsException("Products not found for fields " + field);
	}

	@Override

	public List<ProductsDto> findByProdStatusSold() {

		List<Products> list = productRepo.findByProdStatusSold();
		if (!list.isEmpty()) {
			List<ProductsDto> dtos = new ArrayList<>();
			for (Products products : list) {
				ProductsDto dto = new ProductsDto();
				BeanUtils.copyProperties(products, dto);
				dto.setSupplierId(products.getSupplierId().getId());
				System.out.println(products.getSupplierId().getId());
				dtos.add(dto);
			}

			return dtos;
		}
		throw new ProductsException("No Products with status 'Sold' found");

	}

}
