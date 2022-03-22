package com.capg.rest.repos;


import com.capg.rest.*;
import com.capg.rest.entity.Employee;
import com.capg.rest.repository.EmployeeRepository;

import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
	import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;import static org.junit.jupiter.api.Assertions.*;@DataJpaTest
	public class EmployeeRepositoryTest 
	{
		@Autowired
	private EmployeeRepository employeeRepository; @Autowired
	private TestEntityManager entityManager; @BeforeEach
	void setUp() {
	Employee employee =
	Employee.builder()
	.employeeName("Mechanical Engineering")
	.employeeCode("ME - 011")
	.employeeAddress("Delhi")
	.build(); entityManager.persist(employee);
	} @Test
	void whenFindById_thenReturnEmployee() {
	Employee employee = employeeRepository.findById(1L).get();
	assertEquals("Mechanical Engineering",employee.getEmployeeName());
	}
	}
