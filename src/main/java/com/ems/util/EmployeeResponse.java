package com.ems.util;

import java.util.List;

import lombok.Data;

@Data
public class EmployeeResponse extends Response {
	
	private List<EmployeeDetails> employees;

}
