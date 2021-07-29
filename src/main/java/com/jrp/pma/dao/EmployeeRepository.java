package com.jrp.pma.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;

@RepositoryRestResource(collectionResourceRel="apiemployees", path="apiemployees")
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	@Query(nativeQuery = true, value = "SELECT  e.first_name AS firstName, e.last_name AS lastName, "
			+ "COUNT(pe.employee_id) AS projectCount FROM employee e "
			+ "LEFT JOIN project_employee pe ON e.employee_id = pe.employee_id "
			+ "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
	public List<EmployeeProject> employeeProjects();

	public Employee findByEmail(String value);

	public Iterable<Employee> findAll(Pageable pageAndSize);

	public Employee findByEmployeeId(long theId);

}
