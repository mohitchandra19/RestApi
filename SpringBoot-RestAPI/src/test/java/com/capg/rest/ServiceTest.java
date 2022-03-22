package com.capg.rest;

import com.capg.rest.entity.Employee;
import com.capg.rest.repository.EmployeeRepository;
import com.capg.rest.service.EmployeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	@BeforeEach
	void setUp() {
		Employee employee = Employee.builder().employeeName("IT").employeeAddress("Ahmedabad").employeeCode("IT-06")
				.employeeId(1L).build();

		Mockito.when(employeeRepository.findByEmployeeNameIgnoreCase("IT")).thenReturn(employee);

	}

	@Test
	@DisplayName("Get Data based on Valid Employee Name")
	void whenValidEmployeeName_thenEmployeeShouldFound() {
		String employeeName = "IT";
		Employee found = employeeService.fetchEmployeeByName(employeeName);

		assertEquals(employeeName, found.getEmployeeName());
	}
}
