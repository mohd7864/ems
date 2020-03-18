package com.ems.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class EmployeeDetails {
	
	private Long id;
	private String name;
	private Integer age;
	private Integer salary;

}
