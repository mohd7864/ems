package com.ems.util;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmployeeRequest {
	
	@NotNull(message = "Name should not be null")
	@Size(min=3, message="Name Should be atleast 3 Charecters")
	private String  name;
	@Positive(message = "Age should be in Positive number")
	private Integer age;
	@Positive(message = "Salary should be in Positive Number")
	private Integer salary;

}
