package com.saleshistory.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Products implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// primary key
	@Id
	@TableGenerator(initialValue = 149, name = "prod_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_id")
	private Integer prodId;

	@Column(name = "prod_name")
	private String prodName;

	@Column(name = "prod_desc")
	private String prodDesc;

	@Column(name = "prod_subcategory")
	private String prodSubcategory;

	@Column(name = "prod_subcategory_id")
	private Integer prodSubcategoryId;

	@Column(name = "prod_subcategory_desc")
	private String prodSubcategoryDesc;

	@Column(name = "prod_category")
	private String prodCategory;

	@Column(name = "prod_category_id")
	private Integer prodCategoryId;

	@Column(name = "prod_category_desc")
	private String prodCategoryDesc;

	@Column(name = "prod_weight_class")
	private Integer prodWeightClass;

	@Column(name = "prod_unit_of_measure")
	private String prodUnitOfMeasure;

	@Column(name = "prod_pack_size")
	private String prodPackSize;

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplierId;

	@Column(name = "prod_status")
	private String prodStatus;

	@Column(name = "prod_list_price")
	private Double prodListPrice;

	@Column(name = "prod_min_price")
	private Double prodMinPrice;

	@Column(name = "prod_total")
	private String prodTotal;

	@Column(name = "prod_total_id")
	private Integer prodTotalId;

	@Column(name = "prod_src_id")
	private Integer prodSrcId;

	@Column(name = "prod_eff_from")
	private LocalDate prodEffFrom;

	@Column(name = "prod_eff_to")
	private LocalDate prodEffTo;

	@Column(name = "prod_valid")
	private Character prodValid;

}