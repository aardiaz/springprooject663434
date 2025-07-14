package com.appsoft.springproject.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appsoft.springproject.model.Employee;
import com.appsoft.springproject.model.User;
import com.appsoft.springproject.repository.EmployeeRepository;
import com.appsoft.springproject.repository.ProductRepository;
import com.appsoft.springproject.service.UserService;
import com.appsoft.springproject.utils.VerifyRecaptcha;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductRepository prodRepo;
	
	@GetMapping("/")
	public String getIndex(Model model) {
		
		model.addAttribute("prodList",prodRepo.findAll());
		return "CustomerHome";
	}

	@GetMapping("/login")
	public String getLogin() {

		return "LoginForm";
	}

	@PostMapping("/login")
	public String postLogin(@ModelAttribute User u, Model model, HttpSession session,
			@RequestParam("g-recaptcha-response") String gRCode) throws IOException {

		// if(VerifyRecaptcha.verify(gRCode)) {

		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		User usr = userService.userLogin(u.getUsername(), u.getPassword());

		if (usr != null) {

			log.info("-------user login success ----------");

			List<Employee> emps = empRepo.findByDepartmentName("admin");
			System.out.println(emps);

			session.setAttribute("activeuser", usr);
			session.setMaxInactiveInterval(400);
			// model.addAttribute("uname",usr.getFname());

			if (usr.getRole().equals("CUSTOMER")) {

				return "CustomerHome";
			}

			return "Home";
		}
//			}else {
//				log.info("------- login failed !!!--------");
//				model.addAttribute("message","user not found !!");
//				return "LoginForm";
//			}
//	}

		log.info("------- login failed !!!--------");
		model.addAttribute("message", "are you Robot !!");
		return "LoginForm";
	}

	@GetMapping("/signup")
	public String getSignup(Model model) {
		model.addAttribute("uModel", new User());
		return "SignupForm";
	}

	@PostMapping("/signup")
	public String postSignup(@ModelAttribute User u) {

		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));

		userService.userSignup(u);
		return "LoginForm";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		log.info("-------- user logout ---------------");
		session.invalidate();// kill session
		return "LoginForm";
	}

	@GetMapping("/profile")
	public String getProfile() {

		return "Profile";
	}

	@GetMapping("/home")
	public String home() {

		return "Home";
	}

	/*
	 * @PostMapping("/signup") public String postSignup(@Valid @ModelAttribute User
	 * u, BindingResult result,Model model) {
	 * 
	 * // if(result.hasErrors()) { // model.addAttribute("uModel",u); //
	 * System.out.println("------error--------------"); // return "SignupForm"; // }
	 * 
	 * userService.userSignup(u); return "LoginForm"; }
	 */

}
