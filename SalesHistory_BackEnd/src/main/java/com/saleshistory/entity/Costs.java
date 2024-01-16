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
import lombok.Data;

@Entity
@Table(name = "costs")
@Data
public class Costs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cost_id")
	private Integer costId;
	// foreign key

	@Id
	@ManyToOne
	@JoinColumn(name = "prod_id", referencedColumnName = "prod_id")
	private Products prodId;

	// foreign key

	@Id
	@ManyToOne
	@JoinColumn(name = "time_id", referencedColumnName = "time_id")
	private Times timeId;

	// foreign key

	@Id
	@ManyToOne
	@JoinColumn(name = "promo_id", referencedColumnName = "promo_id")
	private Promotions promoId;

	// foreign key

	@Id
	@ManyToOne
	@JoinColumn(name = "channel_id", referencedColumnName = "channel_id")
	private Channels channelId;

	@Column(name = "unit_cost")
	private Double unitCost;

	@Column(name = "unit_price")
	private Double unitPrice;

}