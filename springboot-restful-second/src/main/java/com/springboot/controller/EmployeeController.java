package com.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Employee;
import com.springboot.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	// Build create employees REST API
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	// Build get all employees REST API
	@GetMapping("/all")
	public List<Employee> getAllEmployees(){
		return employeeService.findAllEmployee();
	}
	
	// Build get employee by id REST API
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id){
		return new ResponseEntity<Employee>(employeeService.findEmployeeById(id), HttpStatus.OK);
	}
	
	// Build update employee REST API
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id") int id){
		return new ResponseEntity<Employee>( employeeService.updateEmployee(employee, id), HttpStatus.OK);
	}
	
	// Build delete employee REST API
	@DeleteMapping("{id}")
	public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("id") int id){
		return new ResponseEntity<Employee>(employeeService.deleteEmployee(id), HttpStatus.OK);
	}
}
