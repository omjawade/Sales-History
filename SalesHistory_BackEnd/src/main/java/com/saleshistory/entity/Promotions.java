package com.saleshistory.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "promotions")
@Data
public class Promotions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "promo_id")
	private int promoId;

	@Column(name = "promo_name")
	private String promoName;

	@Column(name = "promo_subcategory")
	private String promoSubcategory;

	@Column(name = "promo_subcategory_id")
	private int promoSubcategoryId;

	@Column(name = "promo_category")
	private String promoCategory;

	@Column(name = "promo_category_id")
	private int promoCategoryId;

	@Column(name = "promo_cost")
	private double promoCost;

	@Column(name = "promo_begin_date")
	private LocalDate promoBegindate;

	@Column(name = "promo_end_date")
	private LocalDate promoEndDate;

	@Column(name = "promo_total")
	private String promoTotal;

	@Column(name = "promo_total_id")
	private String promoTotalId;

}
