package com.appsoft.springproject.api;

import java.util.Arrays;
import java.util.List;

import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.appsoft.springproject.ResourceNotFoundException;
import com.appsoft.springproject.model.Employee;
import com.appsoft.springproject.model.Product;
import com.appsoft.springproject.repository.EmployeeRepository;
import com.appsoft.springproject.repository.ProductRepository;
import com.appsoft.springproject.service.EmployeeService;

@RestController
public class EmployeeRestController {
	
	@Autowired
	private ProductRepository  prodRepo;
	
	@Autowired
	private EmployeeRepository  empRepo;
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/api/emp/list")
	public List<Employee> getEmps() {
		
		return empService.getAllEmps();
	}
	
	@PostMapping("/api/emp/add")
	public String add(@RequestBody Employee emp) {
		
		empService.addEmp(emp);
		
		return "added success";
	}
	
	@GetMapping("/api/emp/{id}")
	public  Employee getOne(@PathVariable("id") long id) {
		
		 return empRepo.findById(id).orElseThrow(
				 ()-> new ResourceNotFoundException("employee not found"));
	}
	
	@DeleteMapping("/api/emp/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		
		empService.deleteEmp(id);
		return new  ResponseEntity<String>("deleted success",HttpStatus.OK);
	}
	
	@PutMapping("/api/emp/update")
	public String update(@RequestBody Employee emp) {
		
		empService.updateEmp(emp);
		return "update success";
	}
	
	@GetMapping("/api/emp/j2o")
	public String jsonToObject() {
		
		RestTemplate  temp = new RestTemplate();
		Employee emp = temp.getForObject("http://localhost:9090/api/emp/1", Employee.class);
		
		return "FirstName : "+emp.getFname();
	}
	
	@GetMapping("/api/emp/ja2oa")
	public  String jsonArry2objArray() {
		
		RestTemplate  temp = new RestTemplate();
		Employee[]  emps = temp.getForObject("http://localhost:9090/api/emp/list", Employee[].class);
		
		return "Name : "+emps[2].getFname()+" "+emps[2].getLname();
	}
	
	@GetMapping("/api/emp/products")
	public String loadProducts() {
		
		RestTemplate  temp = new RestTemplate();
		Product[] prodList = temp.getForObject("https://fakestoreapi.com/products", Product[].class);
		
		prodRepo.saveAll(Arrays.asList(prodList));
		
		return "success";
	}
	

}
