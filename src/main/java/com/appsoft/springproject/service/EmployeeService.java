package com.appsoft.springproject.service;

import java.util.List;

import com.appsoft.springproject.model.Employee;

public interface EmployeeService {

	void addEmp(Employee emp);

	void deleteEmp(long id);

	void updateEmp(Employee emp);

	Employee getEmpById(long id);

	List<Employee> getAllEmps();

}
