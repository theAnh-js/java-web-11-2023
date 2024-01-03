package com.springboot.service;

import java.util.List;

import com.springboot.model.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);
	
	List<Employee> findAllEmployee();
	
	Employee findEmployeeById(int id);
	
	Employee updateEmployee(Employee employee, int id);
	
	Employee deleteEmployee(int id);
	
}

