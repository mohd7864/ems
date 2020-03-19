package com.ems.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ems.entity.Employee;
import com.ems.repository.EMSRepository;
import com.ems.util.ApiGetResponse;
import com.ems.util.ApiPostResponse;
import com.ems.util.EmpGetResp;
import com.ems.util.EmployeeDetails;
import com.ems.util.EmployeeRequest;
import com.ems.util.EmployeeResponse;
import com.ems.util.MessageResponse;
import com.ems.util.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EMSServiceImpl implements EMSService {

	@Autowired
	RestTemplate restTemplate;

	public final EMSRepository repository;

	@Override
	@Async("asyncExecutor")
	public ResponseEntity<Response> addEmployee(EmployeeRequest req) {
		ResponseEntity<Response> entity = null;
		String url = "http://dummy.restapiexample.com/api/v1/create";
		String ename = validateEname(req.getName());
		req.setName(ename);
		CompletableFuture<ApiPostResponse> resp = callPostService(req, url);
		CompletableFuture<Employee> status = insertEmployee(req);
		CompletableFuture.allOf(resp,status).join();
		MessageResponse response = new MessageResponse();
		response.setMsg("Employee Data Posted Successfully");
		response.setCode(200);
		entity = new ResponseEntity<>(response, HttpStatus.OK);
		return entity;
	}

	public CompletableFuture<Employee> insertEmployee(EmployeeRequest req) {
		Employee emp = new Employee();
		emp.setName(req.getName());
		emp.setAge(req.getAge());
		emp = repository.saveAndFlush(emp);
		return CompletableFuture.completedFuture(emp);
	}

	public String validateEname(String ename) {
		List<EmployeeDetails> employees = repository.findByNameContaining(ename);
		int size = employees.size();
		if (size >= 1) {
			ename = ename + (size + 1);
		}
		return ename;
	}

	public ApiGetResponse callGetService(String url) {
		ResponseEntity<ApiGetResponse> empRes = restTemplate.getForEntity(url, ApiGetResponse.class);
		return empRes.getBody();
	}

	public CompletableFuture<ApiPostResponse> callPostService(EmployeeRequest req, String url) {
		HttpEntity<EmployeeRequest> request = new HttpEntity<>(req);
		ResponseEntity<ApiPostResponse> empRes = restTemplate.postForEntity(url, request, ApiPostResponse.class);
		return CompletableFuture.completedFuture(empRes.getBody());
	}

	@Override
	public ResponseEntity<Response> searchEmployee(Optional<String> name, Optional<Integer> age) {
		ResponseEntity<Response> entity = null;
		String url = "http://dummy.restapiexample.com/api/v1/employees";
		String ename = name.orElseGet(() -> "test");
		Integer eage = age.orElseGet(() -> 0);
		ApiGetResponse empResp = callGetService(url);
		List<EmpGetResp> empList1 = empResp.getData();
		List<EmployeeDetails> employees = repository.findAllEmployees(ename, eage);
		EmployeeResponse resp = new EmployeeResponse();
		resp.setEmployees(employees);
		entity = new ResponseEntity<>(resp, HttpStatus.OK);
		return entity;
	}

	@Override
	public ResponseEntity<Response> addEmployeeBulk() {
		// TODO Auto-generated method stub
		return null;
	}

}
