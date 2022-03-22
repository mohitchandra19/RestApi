package com.capg.rest.service;

import com.capg.rest.entity.Employee;
import com.capg.rest.error.EmployeeNotFoundException;
import com.capg.rest.repository.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	private final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public Employee saveEmployee(Employee employee) {
		LOGGER.info("Inside saveEmployee of EmployeeService");
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> fetchEmployeeList() {
		LOGGER.info("Inside fetchEmployeeList of EmployeeService");
		return employeeRepository.findAll();
	}

	@Override
	public Employee fetchEmployeeById(Long employeeId) throws EmployeeNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(employeeId);

		if (!employee.isPresent()) {
			throw new EmployeeNotFoundException("Employee Not Available");
		}
		return employee.get();

	}

	@Override
	public void deleteEmployeeById(Long employeeId) {
		LOGGER.info("Inside deleteEmployee of EmployeeService");
		employeeRepository.deleteById(employeeId);
	}

	@Override
	public Employee updateEmployee(Long employeeId, Employee employee) {
		LOGGER.info("Inside updateEmployee of EmployeeService");
		Employee depDB = employeeRepository.findById(employeeId).get();

		if (Objects.nonNull(employee.getEmployeeName()) && !"".equalsIgnoreCase(employee.getEmployeeName())) {
			depDB.setEmployeeName(employee.getEmployeeName());
		}

		if (Objects.nonNull(employee.getEmployeeCode()) && !"".equalsIgnoreCase(employee.getEmployeeCode())) {
			depDB.setEmployeeCode(employee.getEmployeeCode());
		}

		if (Objects.nonNull(employee.getEmployeeAddress()) && !"".equalsIgnoreCase(employee.getEmployeeAddress())) {
			depDB.setEmployeeAddress(employee.getEmployeeAddress());
		}
		return employeeRepository.save(depDB);
	}
	@Override
	public Employee fetchEmployeeByName(String employeeName) {
	LOGGER.info("Inside fetchEmployee of EmployeeService");
	return employeeRepository.findByEmployeeNameIgnoreCase(employeeName);
	}
	}


