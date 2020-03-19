package com.ems.controller;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.service.EMSService;
import com.ems.util.ApiGetResponse;
import com.ems.util.EmployeeRequest;
import com.ems.util.MessageResponse;
import com.ems.util.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Employee Management API")
@RequestMapping("/api/v1")
public class EMSController {
	
	@Autowired
	EMSService ems;
	
	@GetMapping("/healthcheck")
	@ApiOperation(value="Health Check API")
	@ApiResponses(value={@ApiResponse(code = 200, message = "", response = MessageResponse.class)})
	public ResponseEntity<Response> healthcheck(){
		MessageResponse response = new MessageResponse();
		response.setMsg("Employee Management System is Up and Running...");
		response.setCode(200);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping("/addEmployee")
	@ApiOperation(value="Employee Create API")
	@ApiResponses(value={@ApiResponse(code = 201, message = "Employee Created Succesfully", response = MessageResponse.class)})
	public ResponseEntity<Response> addEmployee(@Valid @RequestBody EmployeeRequest req){
		return ems.addEmployee(req);
	}
	
	@GetMapping("/searchEmployee")
	@ApiOperation(value="Employee Search API")
	@ApiResponses(value={@ApiResponse(code = 200, message = "Employee Search Api", response = ApiGetResponse.class)})
	public ResponseEntity<Response> searchEmployee(@RequestParam Optional<String> name,@RequestParam Optional<Integer> age){
		return ems.searchEmployee(name,age);
	}

}
