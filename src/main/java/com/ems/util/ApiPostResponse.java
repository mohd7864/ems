package com.ems.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class ApiPostResponse {
	
	private String status;
	EmployeeDetails data;

}
