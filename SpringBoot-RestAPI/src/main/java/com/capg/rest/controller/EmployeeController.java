package com.capg.rest.controller;

import com.capg.rest.entity.Employee;
import com.capg.rest.error.EmployeeNotFoundException;
import com.capg.rest.repository.EmployeeRepository;
import com.capg.rest.service.EmployeeService;
import com.capg.rest.entity.Employee;
import com.capg.rest.entity.Employee;
import com.capg.rest.error.EmployeeNotFoundException;
import com.capg.rest.entity.Employee;
import com.capg.rest.error.EmployeeNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeRepository repo;

	private final Logger my_logger = LoggerFactory.getLogger(EmployeeController.class);

	@PostMapping("/employees")
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
        my_logger.info("Inside saveEmployee of EmployeeController");
        return employeeService.saveEmployee(employee);
    }


	@GetMapping("/employees")
	public List<Employee> fetchEmployeeList() {
		try {
			my_logger.info("Fetching all Employee records" + employeeService.fetchEmployeeList());
			return employeeService.fetchEmployeeList();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The HTTP Status will be 404 Not Found Error.");
		}
	}

	 @GetMapping("/employees/{id}")
	    public Employee fetchEmployeeById(@PathVariable("id") Long employeeId)
	            throws EmployeeNotFoundException {
	        return employeeService.fetchEmployeeById(employeeId);
	    }

	@DeleteMapping("/employees/{id}")
	public String deleteEmployeeById(@PathVariable("id") Long employeeId) {
		try {
			my_logger.info("Deleting employees records by ID" + " " + employeeId);
			employeeService.deleteEmployeeById(employeeId);
			Predicate<Long> pr = i -> (i == employeeId);
			System.out.println(employeeId + " " + "This id is deleted sucessfully " + pr.test(employeeId));
			return "Employee deleted Successfully!!";
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.ACCEPTED,
					"HTTP status, 202 the action has not yet been deleted");
		}
	}

	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable("id") Long employeeId, @RequestBody Employee employee) {
		try {
			my_logger.info("Updating employees records by ID" + " " + employeeId);
			Stream<Employee> anStream = Stream.of(employeeService.updateEmployee(employeeId, employee));
			List<Employee> resultList = anStream.collect(Collectors.toList());
			System.out.println("updated employee list " + resultList);
			return employeeService.updateEmployee(employeeId, employee);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "HTTP Status will be NOT FOUND (CODE 400)\n");
		}

	}

	@PatchMapping("/employees/{id}")
	public Employee updatedEmployee(@PathVariable("id") Long employeeId, @RequestBody Employee employeeCode) {
		try {
			my_logger.info("Partially Updating employees records by ID" + " " + employeeId);
			Stream<Employee> anStream = Stream.of(employeeService.updateEmployee(employeeId, employeeCode));
			List<Employee> resultList = anStream.collect(Collectors.toList());
			System.out.println(" partial updated employee list " + resultList);
			return employeeService.updateEmployee(employeeId, employeeCode);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "The HTTP Status will be 500");
		}

	}

	@GetMapping("/employees/name/{name}")
	public Employee fetchEmployeeByName(@PathVariable("name") String employeeName) {
		List<Employee> name = (List<Employee>) employeeService.fetchEmployeeByName(employeeName);
		List<Employee> predict = (List<Employee>) name.stream().toList();
		System.out.println("Get the fetched employee name" + predict);
		return employeeService.fetchEmployeeByName(employeeName);
	}
}