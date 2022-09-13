package com.smsapi.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@JsonFilter("myFilter")
public class MyResponseFilter {

	public void doFilter() {
		SimpleBeanPropertyFilter codesFilter = SimpleBeanPropertyFilter
				  .serializeAllExcept("errors");
		FilterProvider filters = new SimpleFilterProvider()
				  .addFilter("myFilter", codesFilter);
	}
}
