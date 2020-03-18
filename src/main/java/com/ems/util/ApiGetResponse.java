package com.ems.util;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class ApiGetResponse {
	
	private String status;
	List<EmpGetResp> data;

}
