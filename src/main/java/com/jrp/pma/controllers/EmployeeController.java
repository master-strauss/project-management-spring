package com.jrp.pma.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	//Dont tie Controller class directly to repositories, use Service to handle 
	// repositories.
	
	@Autowired
	EmployeeService employeeService;

	@GetMapping
	public String displayEmployees(Model model) {
		Iterable<Employee> employees = employeeService.getAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}

	/**
	 * Model is used to exchange data between the view and controller
	 */
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {

		Employee anEmployee = new Employee();

		model.addAttribute("employee", anEmployee);

		return "employees/new-employee";
	}

	@PostMapping("/save")
	public String createEmployee( Model model, @Valid Employee employee, Errors errors) {
		
		if (errors.hasErrors())
			return "employees/new-employee";

		// this should handle saving to the DB.
		employeeService.save(employee);

		// Use a redirect to prevent duplicate submissions
		return "redirect:/employees";
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {
		Employee theEmp = employeeService.findByEmployeeId(theId);
		model.addAttribute("employee", theEmp);
		
		return "employees/new-employee";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long theId, Model model) {
		Employee theEmp = employeeService.findByEmployeeId(theId);
		employeeService.delete(theEmp);
		
		return "redirect:/employees";
	}
	

	/**
	 * Spring container injects an auto-created instance of the employee repository
	 * Spring Framework creates an instance of the interface EmployeeRepository
	 */
//  ### FIELD INJECTION (Requires @Autowired) ###
//	@Autowired
//	EmployeeRepository empRepo;

//  ### CONSTRUCTOR INJECTION - refactoring (DOES NOT REQUIRE @Autowired) ###
//	EmployeeRepository empRepo;

//	public EmployeeController(EmployeeRepository empRepo) {
//		this.empRepo = empRepo;
//	}

//  ### SETTER INJECTION (Requires @Autowired in method) ###
//	EmployeeRepository empRepo;

//	@Autowired
//	public void setEmpRepo(EmployeeRepository empRepo) {
//		this.empRepo = empRepo;
//	}

}
