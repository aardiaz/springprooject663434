package com.appsoft.springproject;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.appsoft.springproject.model.Department;
import com.appsoft.springproject.model.Employee;
import com.appsoft.springproject.repository.EmployeeRepository;

@Component
public class Test implements CommandLineRunner {
	
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public void run(String... args) throws Exception {
		 
//		
//		List<Employee>  emps = empRepo.findByDepartmentName("admin");
//		System.out.println(emps);
		
	}

}
