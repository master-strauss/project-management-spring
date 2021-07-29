package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;
import com.jrp.pma.springexample.Car;

@Controller
public class HomeController {

	@Value("${app.version}")
	private String ver;

	/**
	 * Spring container injects an autocreated instance of the project repository
	 * Spring Framework creates an instance of the interface ProjectRepository
	 */

	@Autowired
	Car car;

	@Autowired
	ProjectRepository proRepo;

	@Autowired
	EmployeeRepository empRepo;

	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		model.addAttribute("versionNumber", ver);

//		Map<String, Object> map = new HashMap<>();

		// Querying the DB for projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList", projects);

		List<ChartData> projectData = proRepo.getProjectStatus();

		// Lets convert a projectData object into a json structure for use in JavaScript
		ObjectMapper objectMapper = new ObjectMapper();

		String jsonString = objectMapper.writeValueAsString(projectData);
		// [["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 3]]
		model.addAttribute("projectStatusCount", jsonString);

		List<EmployeeProject> employeesProjectCount = empRepo.employeeProjects();
		model.addAttribute("employeesListProjectCount", employeesProjectCount);
		return "main/home";

	}

}
