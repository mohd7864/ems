package com.ems.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class EmpGetResp {
	
	private Long id;
	private String employee_name;
	private Integer employee_age;
	private Integer employee_salary;


}
