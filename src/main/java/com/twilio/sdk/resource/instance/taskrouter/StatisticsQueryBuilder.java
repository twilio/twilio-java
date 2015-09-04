package com.twilio.sdk.resource.instance.taskrouter;

import java.util.Calendar;
import java.util.Map;

public class StatisticsQueryBuilder {

	private Calendar startDate;
	private Calendar endDate;
	private Integer minutes;
	private Map<String, String> filters;
	
	public StatisticsQueryBuilder(final Calendar startDate, final Calendar endDate) {
		this(startDate, endDate, null);
	}
	
	public StatisticsQueryBuilder(final Calendar startDate, final Calendar endDate, Map<String, String> filters) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.filters = filters;
	}
	
	public StatisticsQueryBuilder(final Integer minutes) {
		this(minutes, null);
	}
	
	public StatisticsQueryBuilder(final Integer minutes, Map<String, String> filters) {
		this.minutes = minutes;
		this.filters = filters;
	}
	
	public Calendar getStartDate() {
		return startDate;
	}
	
	public Calendar getEndDate() {
		return endDate;
	}
	
	public Integer getMinutes() {
		return minutes;
	}
	
	public Map<String, String> getFilters() {
		return filters;
	}
}
