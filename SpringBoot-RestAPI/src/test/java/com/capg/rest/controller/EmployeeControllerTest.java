package com.capg.rest.controller;

import com.capg.rest.entity.Employee;
import com.capg.rest.error.EmployeeNotFoundException;
import com.capg.rest.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .employeeAddress("Ahmedabad")
                .employeeCode("IT-06")
                .employeeName("IT")
                .employeeId(1L)
                .build();
    }

    @Test
    void saveEmployee() throws Exception {
        Employee inputEmployee = Employee.builder()
                .employeeAddress("Ahmedabad")
                .employeeCode("IT-06")
                .employeeName("IT")
                .build();

        Mockito.when(employeeService.saveEmployee(inputEmployee))
                .thenReturn(employee);

        mockMvc.perform(post("/employees")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" +
                "\t\"employeeName\":\"IT\",\n" +
                "\t\"employeeAddress\":\"Ahmedabad\",\n" +
                "\t\"employeeCode\":\"IT-06\"\n" +
                "}"))
                .andExpect(status().isOk());

    }

    @Test
    void fetchEmployeeById() throws Exception {
        Mockito.when(employeeService.fetchEmployeeById(1L))
                .thenReturn(employee);

        mockMvc.perform(get("/employees/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.employeeName").
                value(employee.getEmployeeName()));
    }
}