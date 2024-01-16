package com.saleshistory.dto;

import java.time.LocalDate;

import com.saleshistory.entity.Countries;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomersDto {

	
	private Integer custId;

	private String custFirstName;

	private String custLastName;

	private Character custGender;

	@NotNull(message = "custYearOfBirth can not be null")
	private Integer custYearOfBirth;

	private String custMaritalStatus;

	private String custStreetAddress;

	private String custPostalCode;

	private String custCity;

	@NotNull(message = "custCityId can not be null")
	private Integer custCityId;

	private String custStateProvince;

	@NotNull(message = "custStateProvinceId can not be null")
	private Integer custStateProvinceId;


	@NotNull(message = "countryId can not be null")
	private Integer countryId;

	private String custMainPhoneINT;

	private String custIncomeLevel;

	private Integer custCreditLimit;

	@NotBlank(message = "custEmail must be in the format")
	private String custEmail;

	private String custTotal;

	@NotNull(message = "custTotalId can not be null")
	private Integer custTotalId;

	private Integer custSrcId;

	private LocalDate custEffFrom;

	private LocalDate custEffTo;

	private Character custValid;

	@NotBlank(message = "Phone number can not be null")
	private String custMainPhoneNumber;
}
