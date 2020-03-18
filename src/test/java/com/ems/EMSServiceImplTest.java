package com.ems;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.ems.entity.Employee;
import com.ems.repository.EMSRepository;
import com.ems.service.EMSServiceImpl;
import com.ems.util.ApiPostResponse;
import com.ems.util.EmployeeDetails;
import com.ems.util.EmployeeRequest;
import com.ems.util.Response;

@RunWith(MockitoJUnitRunner.class)
public class EMSServiceImplTest {
	
	EMSServiceImpl emsServiceImpl;
	
	@Mock
	EMSRepository repository;
	
	@Before
	public void setup(){
		emsServiceImpl = new EMSServiceImpl(repository);
	}
	
	
	@Test
	public void test_addEmployee() {
		String ename = "Test";
		EmployeeRequest req = new EmployeeRequest();
		req.setName("Test");
		req.setAge(20);
		req.setSalary(2000);
		EMSServiceImpl emsServiceImplSpy = spy(emsServiceImpl);
		List<EmployeeDetails> details = new ArrayList<EmployeeDetails>();
		EmployeeDetails emp = EmployeeDetails.builder().id(1L).age(20).name("test").salary(2000).build();
		details.add(emp);
		ApiPostResponse postResp = ApiPostResponse.builder().status("success").data(emp).build();
		doReturn(ename).when(emsServiceImplSpy).validateEname(any());
		doReturn(postResp).when(emsServiceImplSpy).callPostService(any(), any());
		Employee empDt = new Employee();
		empDt.setName("Test");
		empDt.setAge(20);
		ResponseEntity<? extends Response> addEmp = emsServiceImplSpy.addEmployee(req);
		assertEquals(201, addEmp.getStatusCode().value());
	}

}
