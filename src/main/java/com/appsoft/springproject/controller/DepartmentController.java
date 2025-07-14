package com.appsoft.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.appsoft.springproject.model.Department;
import com.appsoft.springproject.service.DepartmentService;
import com.appsoft.springproject.utils.DepartmentExcelView;
import com.appsoft.springproject.utils.DepartmentPdfView;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService deptService;

	@GetMapping("/department")
	public String getDepartmentForm() {

		return "DepartmentForm";
	}

	@PostMapping("/department")
	public String postDepartment(@ModelAttribute Department dept) {

		deptService.addDepartment(dept);
		return "DepartmentForm";
	}

	@GetMapping("/departmentList")
	public String getAll(Model model) {

		model.addAttribute("dList", deptService.getAllDepartments());
		return "DepartmentListForm";
	}

	@GetMapping("/dept/delete")
	public String deleteDept(@RequestParam("id") int id) {

		deptService.deleteDepartment(id);

		return "redirect:/departmentList";
	}

	@GetMapping("/dept/edit")
	public String editDept(@RequestParam("id") int id, Model model) {

		model.addAttribute("dptObject", deptService.getDepartmentById(id));

		return "DepartmentEditForm";
	}

	@PostMapping("/dept/update")
	public String updateDept(@ModelAttribute Department dept) {

		deptService.updateDepartment(dept);
		return "redirect:/departmentList";
	}

	@GetMapping("/dept/excel")
	public ModelAndView excel() {

		ModelAndView mv = new ModelAndView();

		mv.addObject("dList", deptService.getAllDepartments());
		mv.setView(new DepartmentExcelView());

		return mv;
	}

	@GetMapping("/dept/pdf")
	public ModelAndView pdf() {
		ModelAndView mv = new ModelAndView();

		mv.addObject("dList", deptService.getAllDepartments());
		mv.setView(new DepartmentPdfView());

		return mv;
	}

}
