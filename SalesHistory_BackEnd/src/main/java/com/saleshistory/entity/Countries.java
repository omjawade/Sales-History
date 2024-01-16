package com.saleshistory.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.Data;

@Entity
@Table(name = "countries")
@Data
public class Countries implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// primary key

	@Id
	@TableGenerator(initialValue = 52804, name = "country_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_Id")
	private Integer countryId;

	@Column(name = "country_iso_code")
	private String countryIsoCode;

	@Column(name = "country_name")
	private String countryName;

	@Column(name = "country_subregion")
	private String countrySubregion;

	@Column(name = "country_subregion_id")
	private Integer countrySubregionId;

	@Column(name = "country_region")
	private String countryRegion;

	@Column(name = "country_region_id")
	private Integer countryRegionId;

	@Column(name = "country_total")
	private String countryTotal;

	@Column(name = "country_total_id")
	private Integer countryTotalId;

}