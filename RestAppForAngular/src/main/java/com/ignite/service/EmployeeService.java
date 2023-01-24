package com.ignite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignite.entity.Employee;
import com.ignite.exception.InvalidEmployeeIdException;
import com.ignite.repo.EmployeeRepo;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepo employeeRepo;
	
	public Employee findById(int id) throws InvalidEmployeeIdException {
		Optional<Employee> empOptional = employeeRepo.findById(id);
		if(empOptional.isEmpty()) {
			throw new InvalidEmployeeIdException("Invalid employee id : "+id);
		}
		return empOptional.get();
	}
	
	public List<Employee> findAll(){
		return employeeRepo.findAll();
	}
	
	public Employee save(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public Employee update(Employee employee) throws InvalidEmployeeIdException {
		findById(employee.getId());
		return employeeRepo.save(employee);
	}
	
	public Employee delete(int id) throws InvalidEmployeeIdException {
		Employee employee = findById(id);
		employeeRepo.deleteById(id);
		return employee;
	}
}
