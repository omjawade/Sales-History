package com.saleshistory.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TimesDto {

	private LocalDate timeId;

	private String dayName;

	private Integer dayINTInWeek;

	private Integer dayINTInMonth;

	private Integer calendarWeekINT;

	private Integer fiscalWeekINT;

	private LocalDate weekEndingDay;

	private Integer weekEndingDayId;

	private Integer calendarMonthINT;

	private Integer fiscalMonthINT;

	private String calendarMonthDesc;

	private Integer calendarMonthId;

	private String fiscalMonthDesc;

	private Integer fiscalMonthId;

	private Integer daysInCalMonth;

	private Integer daysInFisMonth;

	private LocalDate endOfCalMonth;

	private LocalDate endOfFisMonth;

	private String calendarMonthName;

	private String fiscalMonthName;

	private String calendarQuarterDesc;

	private Integer calendarQuarterId;

	private String fiscalQuarterDesc;

	private Integer fiscalQuarterId;

	private Integer daysInCalQuarter;

	private Integer daysInFisQuarter;

	private LocalDate endOfCalQuarter;

	private LocalDate endOfFisQuarter;

	private Integer calendarQuarterINT;

	private Integer fiscalQuarterINT;

	private Integer calendarYear;

	private Integer calendarYearId;

	private Integer fiscalYear;

	private Integer fiscalYearId;

	private Integer daysInCalYear;

	private Integer daysInFisYear;

	private LocalDate endOfCalYear;

	private LocalDate endOfFisYear;

}
