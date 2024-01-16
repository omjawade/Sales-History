package com.saleshistory.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "supplementary_demographics")
@Data
public class SupplementaryDemographics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cust_id")
	private int custId;

	@Column(name = "education")
	private String education;

	@Column(name = "occupation")
	private String occupation;

	@Column(name = "household_size")
	private String householdSize;

	@Column(name = "yrs_residence")
	private Integer yrsResidence;

	@Column(name = "affinity_card")
	private Integer affinityCard;

	@Column(name = "bulk_pack_diskettes")
	private Integer bulkPackDiskettes;

	@Column(name = "flat_panel_monitor")
	private Integer flatPanelMonitor;

	@Column(name = "home_theater_package")
	private Integer homeTheaterPackage;

	@Column(name = "bookkeeping_application")
	private Integer bookkeepingApplication;

	@Column(name = "printer_supplies")
	private Integer printerSupplies;

	@Column(name = "y_box_games")
	private Integer yBoxGames;

	@Column(name = "os_doc_set_kanji")
	private Integer osDocSetKanji;

	@Column(name = "comments")
	private String comments;
}