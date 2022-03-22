package com.capg.rest.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long EmployeeId) {
		this.employeeId = EmployeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String EmployeeName) {
		this.employeeName = EmployeeName;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String EmployeeAddress) {
		this.employeeAddress = EmployeeAddress;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String EmployeeCode) {
		this.employeeCode = EmployeeCode;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeId;

	@NotBlank(message = "Please Add Employee Name")
	/*
	 * @Length(max = 5,min =1)
	 * 
	 * @Size(max = 10, min = 0)
	 * 
	 * @Email
	 * 
	 * @Positive
	 * 
	 * @Negative
	 * 
	 * @PositiveOrZero
	 * 
	 * @NegativeOrZero
	 * 
	 * @Future
	 * 
	 * @FutureOrPresent
	 * 
	 * @Past
	 * 
	 * @PastOrPresent
	 */
	private String employeeName;
	private String employeeAddress;
	private String employeeCode;

}