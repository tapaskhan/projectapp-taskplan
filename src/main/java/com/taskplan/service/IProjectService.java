package com.taskplan.service;

import java.util.List;

import com.taskplan.model.ProjectBO;

public interface IProjectService {

	List<ProjectBO> findAllProjects();
	ProjectBO createProject(ProjectBO projectBO);
}
