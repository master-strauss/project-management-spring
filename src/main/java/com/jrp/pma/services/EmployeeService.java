package com.jrp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository empRepo;

	public Employee save(Employee employee) {
		return empRepo.save(employee);
	}

	public Iterable<Employee> getAll() {
		return empRepo.findAll();
	}

	public List<EmployeeProject> employeeProject() {
		return empRepo.employeeProjects();
	}

	public Employee findByEmployeeId(long theId) {
		return empRepo.findByEmployeeId(theId);
	}

	public void delete(Employee theEmp) {
		empRepo.delete(theEmp);
	}

//  ### FIELD INJECTION (Requires @Autowired) ###
//	@Qualifier("staffRepositoryImpl1")
//	@Autowired
//	IStaffRepository empRepoField;

//  ### CONSTRUCTOR INJECTION - refactoring (DOES NOT REQUIRE @Autowired) ###
//	IStaffRepository empRepoCons;
//	
//	public EmployeeService(@Qualifier("staffRepositoryImpl1") IStaffRepository empRepo) {
//		super();
//		this.empRepoCons = empRepo;
//	}

//  ### SETTER INJECTION (Requires @Autowired in method) ###
//	IStaffRepository empRepoSetter;

//	@Qualifier("staffRepositoryImpl1")
//	@Autowired
//	public void setEmpRepo(IStaffRepository empRepo) {
//		this.empRepoSetter = empRepo;
//	}

}
