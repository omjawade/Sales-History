package com.saleshistory.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "times")
@Data
public class Times implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "time_id")
	private LocalDate timeId;

	@Column(name = "day_name")
	private String dayName;

	@Column(name = "day_INT_in_week")
	private Integer dayINTInWeek;

	@Column(name = "day_INT_in_month")
	private Integer dayINTInMonth;

	@Column(name = "calendar_week_INT")
	private Integer calendarWeekINT;

	@Column(name = "fiscal_week_INT")
	private Integer fiscalWeekINT;

	@Column(name = "week_ending_day")
	private LocalDate weekEndingDay;

	@Column(name = "week_ending_day_id")
	private Integer weekEndingDayId;

	@Column(name = "calendar_month_INT")
	private Integer calendarMonthINT;

	@Column(name = "fiscal_month_INT")
	private Integer fiscalMonthINT;

	@Column(name = "calendar_month_desc")
	private String calendarMonthDesc;

	@Column(name = "calendar_month_id")
	private Integer calendarMonthId;

	@Column(name = "fiscal_month_desc")
	private String fiscalMonthDesc;

	@Column(name = "fiscal_month_id")
	private Integer fiscalMonthId;

	@Column(name = "days_in_cal_month")
	private Integer daysInCalMonth;

	@Column(name = "days_in_fis_month")
	private Integer daysInFisMonth;

	@Column(name = "end_of_cal_month")
	private LocalDate endOfCalMonth;

	@Column(name = "end_of_fis_month")
	private LocalDate endOfFisMonth;

	@Column(name = "calendar_month_name")
	private String calendarMonthName;

	@Column(name = "fiscal_month_name")
	private String fiscalMonthName;

	@Column(name = "calendar_quarter_desc")
	private String calendarQuarterDesc;

	@Column(name = "calendar_quarter_id")
	private Integer calendarQuarterId;

	@Column(name = "fiscal_quarter_desc")
	private String fiscalQuarterDesc;

	@Column(name = "fiscal_quarter_id")
	private Integer fiscalQuarterId;

	@Column(name = "days_in_cal_quarter")
	private Integer daysInCalQuarter;

	@Column(name = "days_in_fis_quarter")
	private Integer daysInFisQuarter;

	@Column(name = "end_of_cal_quarter")
	private LocalDate endOfCalQuarter;

	@Column(name = "end_of_fis_quarter")
	private LocalDate endOfFisQuarter;

	@Column(name = "calendar_quarter_INT")
	private Integer calendarQuarterINT;

	@Column(name = "fiscal_quarter_INT")
	private Integer fiscalQuarterINT;

	@Column(name = "calendar_year")
	private Integer calendarYear;

	@Column(name = "calendar_year_id")
	private Integer calendarYearId;

	@Column(name = "fiscal_year")
	private Integer fiscalYear;

	@Column(name = "fiscal_year_id")
	private Integer fiscalYearId;

	@Column(name = "days_in_cal_year")
	private Integer daysInCalYear;

	@Column(name = "days_in_fis_year")
	private Integer daysInFisYear;

	@Column(name = "end_of_cal_year")
	private LocalDate endOfCalYear;

	@Column(name = "end_of_fis_year")
	private LocalDate endOfFisYear;

}
