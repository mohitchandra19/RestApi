package com.capg.rest.service;



import com.capg.rest.entity.Employee;
import com.capg.rest.error.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
	public Employee saveEmployee(Employee employee);

	public List<Employee> fetchEmployeeList();

	public Employee fetchEmployeeById(Long employeeId) throws EmployeeNotFoundException;

	public void deleteEmployeeById(Long employeeId);

	public Employee updateEmployee(Long employeeId, Employee employee);

	Employee fetchEmployeeByName(String employeeName);

}
