package com.taskplan.service;

import java.util.List;

import com.taskplan.model.ProjectBO;

public interface IProjectService {

	List<ProjectBO> findAllProjects();
	List<ProjectBO> findAllActiveProjects();
	ProjectBO createProject(ProjectBO projectBO);
	ProjectBO updateProject(String projectId,ProjectBO projectBO);
	ProjectBO updateProjectStatus(String projectId,ProjectBO projectBO);
}
