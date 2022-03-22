package com.capg.rest.repository;

import com.capg.rest.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Employee findByEmployeeName(String departmentName);

	public Employee findByEmployeeNameIgnoreCase(String departmentName);
}