package com.saleshistory.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.Data;

@Entity
@Table(name = "customers")
@Data

public class Customers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// primary key
	@Id
	@TableGenerator(initialValue = 2001, name = "cust_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_id")
	private Integer custId;

	@Column(name = "cust_first_name")
	private String custFirstName;

	@Column(name = "cust_last_name")
	private String custLastName;

	@Column(name = "cust_gender")
	private Character custGender;

	@Column(name = "cust_year_of_birth")
	private Integer custYearOfBirth;

	@Column(name = "cust_marital_status")
	private String custMaritalStatus;

	@Column(name = "cust_street_address")
	private String custStreetAddress;

	@Column(name = "cust_postal_code")
	private String custPostalCode;

	@Column(name = "cust_city")
	private String custCity;

	@Column(name = "cust_city_id")
	private Integer custCityId;

	@Column(name = "cust_state_province")
	private String custStateProvince;

	@Column(name = "cust_state_province_id")
	private Integer custStateProvinceId;

	// foreign key
	// Many Customers can belong to a single country

	@ManyToOne
	@JoinColumn(name = "country_id", referencedColumnName = "country_id")
	private Countries countryId;

	@Column(name = "cust_main_phone_INT")
	private String custMainPhoneINT;

	@Column(name = "cust_income_level")
	private String custIncomeLevel;

	@Column(name = "cust_credit_limit")
	private Integer custCreditLimit;

	@Column(name = "cust_email")
	private String custEmail;

	@Column(name = "cust_total")
	private String custTotal;

	@Column(name = "cust_total_id")
	private Integer custTotalId;

	@Column(name = "cust_src_id")
	private Integer custSrcId;

	@Column(name = "cust_eff_from")
	private LocalDate custEffFrom;

	@Column(name = "cust_eff_to")
	private LocalDate custEffTo;

	@Column(name = "cust_valid")
	private Character custValid;

	@Column(name = "cust_main_phone_number")
	private String custMainPhoneNumber;

}
