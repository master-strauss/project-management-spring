package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dto.TimeChartData;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectService proService;

	@Autowired
	EmployeeService empService;

	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = proService.getAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}

	/**
	 * Model is used to exchange data between the view and controller
	 */
	@GetMapping("/new")
	public String displayProjectForm(Model model) {

		Project aProject = new Project();
		Iterable<Employee> employees = empService.getAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);

		return "projects/new-project";
	}

	@PostMapping("/save")
	public String createProject(Project project, @RequestParam List<Long> employees, Model model) {

		// This should handle saving to the DB.
		proService.save(project);

		// Use a redirect to prevent duplicate submissions
		return "redirect:/projects";
	}
	
	@GetMapping("/update")
	public String displayProjectUpdateForm(@RequestParam("id") long theId, Model model) {
		Project theProj = proService.findByProjectId(theId);
		model.addAttribute("project", theProj);
		Iterable<Employee> employees = empService.getAll();
		model.addAttribute("allEmployees", employees);
		
		return "projects/new-project";
	}
	
	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") long theId, Model model) {
		Project theProj = proService.findByProjectId(theId);
		proService.delete(theProj);
		
		return "redirect:/employees";
	}
	
	@GetMapping("/timelines")
	public String displayProjectTimelines(Model model) throws JsonProcessingException {
		
		List<TimeChartData> timelineData = proService.getTimeData();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimelineString = objectMapper.writeValueAsString(timelineData);

		System.out.println("---------- project timelines ----------");
		System.out.println(jsonTimelineString);
		
		model.addAttribute("projectTimeList", jsonTimelineString);
		
		return "projects/project-timelines";
	}

//	/**
//	 * Spring container injects an autocreated instance of the project repository
//	 * Spring Framework creates an instance of the interface ProjectRepository
//	 */
//	@Autowired
//	ProjectRepository proRepo;
//	
//	@Autowired
//	EmployeeRepository empRepo;

}
