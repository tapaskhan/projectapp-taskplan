package com.taskplan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taskplan.model.ProjectBO;
import com.taskplan.service.impl.ProjectService;

@RestController
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value="/projectapp/projects",method=RequestMethod.GET)
	public ResponseEntity<List<ProjectBO>> getAllProjects(){
		List<ProjectBO> projectBOlist=projectService.findAllProjects();
		return new ResponseEntity<List<ProjectBO>>(projectBOlist,HttpStatus.OK);
	}
	@RequestMapping(value="/projectapp/project",method=RequestMethod.POST)
	public ResponseEntity<ProjectBO> createProject(@RequestBody ProjectBO projectBO){
		ProjectBO savedProjectBO=projectService.createProject(projectBO);
		return new ResponseEntity<ProjectBO>(savedProjectBO,HttpStatus.OK);
	}
	@RequestMapping(value="/projectapp/project/{id}",method=RequestMethod.PUT)
	public ResponseEntity<ProjectBO> updateProject(@PathVariable("id") String projectId,@RequestBody ProjectBO projectBO){
		ProjectBO savedProjectBO=projectService.updateProject(projectId,projectBO);
		return new ResponseEntity<ProjectBO>(savedProjectBO,HttpStatus.OK);
	}
}
