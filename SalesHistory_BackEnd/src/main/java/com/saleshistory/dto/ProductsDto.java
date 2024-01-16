package com.saleshistory.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ProductsDto {

	
	private Integer prodId;

	@NotBlank(message="prodName can not be null")
	private String prodName;

	
	private String prodDesc;

	
	private String prodSubcategory;

	@NotNull(message="prodSubcategoryId can not be null")
	private Integer prodSubcategoryId;

	
	private String prodSubcategoryDesc;

	
	private String prodCategory;

	@NotNull(message="prodCategoryId can not be null")
	private Integer prodCategoryId;


	private String prodCategoryDesc;


	@NotNull(message="prodWeightClass can not be null")
	private Integer prodWeightClass;


	private String prodUnitOfMeasure;


	private String prodPackSize;


	@NotNull(message="supplierId can not be null")
	private Integer supplierId;


	private String prodStatus;


	private Double prodListPrice;


	private Double prodMinPrice;


	private String prodTotal;


	@NotNull(message="prodTotalId can not be null")
	private Integer prodTotalId;


	private Integer prodSrcId;


	private LocalDate prodEffFrom;


	private LocalDate prodEffTo;


	private Character prodValid;
}
