package com.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	// Spring Boot REST API returns Java bean
	@GetMapping("/student" )
	public Student getStudent() {
		return new Student("Chopper", "Tony Tony");
	}
	
	//Create Spring Boot REST API returns List
	@GetMapping("/students")
	public List<Student> getStudentList(){
		List<Student> students = new ArrayList<>();
		students.add(new Student("Luffy", "Monkey D."));
		students.add(new Student("Zoro", "Roronoa"));
		students.add(new Student("Sanji", "Vinsmoke"));
		
		return students;
	}
	
	//Spring Boot REST API with Path Variable - @PathVariable
	@GetMapping("/student/{firstName}/{lastName}")
	public Student studentPathVariable(
			@PathVariable("firstName") String firstName, 
			@PathVariable("lastName") String lastName) {
		
		return new Student(firstName, lastName);
	}
	
	//Spring Boot REST API with Request Param - @RequestParam
	@GetMapping("/mystudent")  // tương đương với:  /student?firstName=...&lastName=...
	public Student studentRequestParam(
			@RequestParam("firstName") String firstName, 
			@RequestParam("lastName") String lastName
			 ) {	
		return new Student(firstName, lastName);	
	}
	
}

//http://localhost:8080/student -> Out put:
//{
	//"firstName": "Chopper",
	//"lastName": "Tony Tony"
//}
