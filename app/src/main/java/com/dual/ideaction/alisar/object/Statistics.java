package com.dual.ideaction.alisar.object;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import org.apache.commons.lang.time.DateUtils;

public class Statistics extends HashMap<Date, Double>{
	
	private static final long serialVersionUID = 1L;

	public Statistics(){
		
		super();
		
	}
	
	public Statistics(Map<String, String> map){
		
		super();
		for (Map.Entry<String, String> entry : map.entrySet()) {
		    Date date = new Date(Long.valueOf(entry.getKey())*1000);
		    this.put(date, Double.valueOf(entry.getValue()));
		}
		
	}
	
	public Statistics getHourly(){
		/*
		Statistics statistics = new Statistics();
		for (Map.Entry<Date, Double> entry : this.entrySet()) {
			Date date = DateUtils.truncate(entry.getKey(), Calendar.HOUR);
			if (statistics.containsKey(date))
				statistics.put(date, statistics.get(date) + entry.getValue());
			else
				statistics.put(date, entry.getValue());
		}
		return statistics;
		*/
		return null;
	}
	
	public Statistics getDaily(){
		/*
		Statistics statistics = new Statistics();
		for (Map.Entry<Date, Double> entry : this.entrySet()) {
			Date date = DateUtils.truncate(entry.getKey(), Calendar.DATE);
			if (statistics.containsKey(date))
				statistics.put(date, statistics.get(date) + entry.getValue());
			else
				statistics.put(date, entry.getValue());
		}
		return statistics;
		*/
		return null;
	}
	
	public Statistics getWeekly(){
		/*
		Statistics statistics = new Statistics();
		for (Map.Entry<Date, Double> entry : this.entrySet()) {
			Date date = DateUtils.truncate(entry.getKey(), Calendar.WEEK_OF_YEAR);
			if (statistics.containsKey(date))
				statistics.put(date, statistics.get(date) + entry.getValue());
			else
				statistics.put(date, entry.getValue());
		}
		return statistics;
		*/
		return null;
	}
	
	public Statistics getMothly(){
		/*
		Statistics statistics = new Statistics();
		for (Map.Entry<Date, Double> entry : this.entrySet()) {
			Date date = DateUtils.truncate(entry.getKey(), Calendar.MONTH);
			if (statistics.containsKey(date))
				statistics.put(date, statistics.get(date) + entry.getValue());
			else
				statistics.put(date, entry.getValue());
		}
		return statistics;
		*/
		return null;
	}
	
	public double getTotalLastMonth(){
		
		double total = 0;
		for (Map.Entry<Date, Double> entry : this.entrySet()) {
			if (isCurrentMonth(entry.getKey()))
				total += entry.getValue();
		}
		return total;
		
	}
	
	
	public double getTotal(){
		
		double total = 0;
		for (Map.Entry<Date, Double> entry : this.entrySet()) {
			total += entry.getValue();
		}
		return total;
		
	}
	
	private boolean isCurrentMonth(Date date){
		
		java.util.Date curdate= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(curdate);
		int curmonth = cal.get(Calendar.MONTH);
		int curyear = cal.get(Calendar.YEAR);
		
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		
		return (curmonth == month && curyear == year);
		
	}
	
	@Override
	public String toString() {
		
		String content = "";
		
		for (Map.Entry<Date, Double> entry : this.entrySet()) {
			content += "<" + entry.getKey() + "," + entry . getValue() + ">";
		}
		
		return "Statistics ["+ content +"]";
		
	}
	
}
