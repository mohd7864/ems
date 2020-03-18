package com.ems.util;

import lombok.Data;

@Data
public class MessageResponse extends Response {
	
	private String msg;
	private Integer code;
}
