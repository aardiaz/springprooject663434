package com.appsoft.springproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appsoft.springproject.model.Department;
import com.appsoft.springproject.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	@Query(value =  "SELECT * FROM Employee_tbl e JOIN department_tbl d WHERE d.dpt_Name = :deptName" , nativeQuery = true )
    List<Employee> findByDepartmentName(@Param("deptName") String deptName);
}
