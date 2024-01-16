package com.saleshistory.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "channels")
@Data
public class Channels implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "channel_id")
	private Integer channelId;

	@Column(name = "channel_desc")
	private String channelDesc;

	@Column(name = "channel_class")
	private Character channelClass;

	@Column(name = "channel_class_id")
	private int channelClassId;

	@Column(name = "channel_total")
	private String channelTotal;

	@Column(name = "channel_total_id")
	private Integer channelTotalId;

}
