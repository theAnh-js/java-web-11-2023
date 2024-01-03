package com.springboot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.exception.ResourceNotFoundException;
import com.springboot.model.Employee;
import com.springboot.repository.EmployeeRepository;
import com.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	// no need @Autowired: giai thich 1
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> findAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findEmployeeById(int id) {
		/*
		 * Optional<Employee> employee = employeeRepository.findById(id);
		 * if(employee.isPresent()) { return employee.get(); }else { throw new
		 * ResourceNotFoundException("Employee", "Id", String.valueOf(id)); }
		 */

		return employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", String.valueOf(id)));

	}

	@Override
	public Employee updateEmployee(Employee employee, int id) {
		
		Employee existEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", String.valueOf(id)));
		
		existEmployee.setFirstName(employee.getFirstName());
		existEmployee.setLastName(employee.getLastName());
		existEmployee.setEmail(employee.getEmail());
		
		employeeRepository.save(existEmployee);
		
		return existEmployee;
	}

	@Override
	public Employee deleteEmployee(int id) {
		
		Employee deleteEmployee = findEmployeeById(id);
		if(deleteEmployee == null) {
			throw new ResourceNotFoundException("Employee", "Id", String.valueOf(id));
		}else {
			employeeRepository.delete(deleteEmployee);
		}
		return deleteEmployee;
	}

}

// 1:
// Starting with Spring 4.3, if a class, which is configured as a Spring bean,
// has only one constructor, the @Autowired annotation can be
// omitted(bỏ qua) and Spring will use constructor and inject 
// all necessary dependencies.
