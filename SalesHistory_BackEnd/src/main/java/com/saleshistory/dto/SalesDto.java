package com.saleshistory.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SalesDto {

	@NotNull(message = "Product Id can not be null")
	private Integer prodId;

	@NotNull(message = "Customer Id can not be null")
	private Integer custId;

	@NotNull(message = "Time Id can not be null")
	private LocalDate timeId;

	@NotNull(message = "Channel Id can not be null")
	private Integer channelId;

	@NotNull(message = "Promotion Id can not be null")
	private Integer promoId;

	private Integer quantitySold;

	private Double amountSold;

	private Integer saleId;
}
