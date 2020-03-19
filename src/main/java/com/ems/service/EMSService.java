package com.ems.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;

import com.ems.util.EmployeeRequest;
import com.ems.util.Response;


public interface EMSService {
	
	public ResponseEntity<Response> addEmployee(EmployeeRequest req);
	public ResponseEntity<Response> searchEmployee(Optional<String> name, Optional<Integer> age);
    public ResponseEntity<Response> addEmployeeBulk();

}
