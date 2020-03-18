package com.ems.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Response {
	@JsonIgnore
    private String response;
}
