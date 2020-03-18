package com.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ems.entity.Employee;
import com.ems.util.EmployeeDetails;

public interface EMSRepository extends JpaRepository<Employee, Long> {
	
	@Query("SELECT new com.ems.util.EmployeeDetails(e.id,e.name,e.age,0) from Employee e where e.name like %:name%")
	List<EmployeeDetails> findByNameContaining(String name);
	
	@Query("SELECT new com.ems.util.EmployeeDetails(e.id,e.name,e.age,0) from Employee e where e.name like %:name% or age=:age")
	List<EmployeeDetails> findAllEmployees(String name, Integer age);

}
