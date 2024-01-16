package com.saleshistory.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CountriesDto {

	
	private Integer countryId;

	private String countryIsoCode;

	private String countryName;

	private String countrySubregion;

	@NotNull(message="countrySubregionId can not be null ")
	private Integer countrySubregionId;

	private String countryRegion;

	@NotNull(message="countryRegionId can not be null ")
	private Integer countryRegionId;

	private String countryTotal;

	@NotNull(message="countryTotalId can not be null ")
	private Integer countryTotalId;

}
