package com.jrp.pma.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jrp.pma.entities.Project;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectRepositoryIntegrationTest2 {

	@Autowired
	ProjectRepository proRepo;

	@Test
	public void ifNewProjectSaved_thenSuccess() {
		Project newProject = new Project("New Test Project", "COMPLETE", "Test Description");
		proRepo.save(newProject);

		assertEquals(1, proRepo.findAll().size());

	}
}