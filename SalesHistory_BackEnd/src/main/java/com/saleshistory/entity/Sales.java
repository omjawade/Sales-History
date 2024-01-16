package com.saleshistory.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@IdClass(SalesId.class)
@Table(name = "sales")
@Data

public class Sales implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "sale_id")
	private Integer saleId;

	@Column(name = "quantity_sold")
	private Integer quantitySold;

	@Column(name = "amount_sold")
	private Double amountSold;
	
	@Id
	@ManyToOne 
	@JoinColumn(name = "prod_id")
	private Products prodId;

	@Id
	@ManyToOne  
	@JoinColumn(name = "cust_id")
	private Customers custId;

	@Id
	@ManyToOne  
	@JoinColumn(name = "time_id")
	private Times timeId;

	@Id
	@ManyToOne
	@JoinColumn(name = "channel_id")
	private Channels channelId;

	@Id
	@ManyToOne  
	@JoinColumn(name = "promo_id")
	private Promotions promoId;
	
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

class SalesId  {

	/**
	 * 
	 */

	
	
	private Products prodId;
	
	private Customers custId;
	
	private Times timeId;
	
	private Channels channelId;
	
	private Promotions promoId;

}

