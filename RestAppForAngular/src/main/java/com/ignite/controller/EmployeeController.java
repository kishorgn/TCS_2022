package com.ignite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignite.entity.Employee;
import com.ignite.exception.InvalidEmployeeIdException;
import com.ignite.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping
	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> employees = employeeService.findAll();
		ResponseEntity<List<Employee>> responseEntity = new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> findById(@PathVariable int id) throws InvalidEmployeeIdException{
		Employee employee = employeeService.findById(id);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(employee, HttpStatus.OK);
		return responseEntity;
	}
	
	@PostMapping
	public ResponseEntity<Employee> save(@RequestBody Employee employee){
		System.out.println("Saving employee "+employee);
		Employee saved = employeeService.save(employee);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(saved, HttpStatus.OK);
		return responseEntity;
	}
	
	@PutMapping
	public ResponseEntity<Employee> update(Employee employee) throws InvalidEmployeeIdException{
		Employee saved = employeeService.update(employee);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(saved, HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping
	public ResponseEntity<Employee> delete(int id) throws InvalidEmployeeIdException{
		Employee employee = employeeService.delete(id);
		ResponseEntity<Employee> responseEntity = new ResponseEntity<Employee>(employee, HttpStatus.OK);
		return responseEntity;
	}
}
