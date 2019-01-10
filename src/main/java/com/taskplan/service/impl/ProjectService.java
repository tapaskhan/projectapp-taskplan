package com.taskplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskplan.dao.entity.ProjectEntity;
import com.taskplan.model.ProjectBO;
import com.taskplan.repository.ProjectRepository;
import com.taskplan.service.IProjectService;
import com.taskplan.service.util.ProjectMapper;
@Service("projectService")
public class ProjectService implements IProjectService {

	@Autowired
	private ProjectRepository projectRepo;
	@Override
	public List<ProjectBO> findAllProjects() {
		List<ProjectEntity> projectEntityList= projectRepo.findAll();
		ProjectMapper mapper=new ProjectMapper();
		List<ProjectBO> projectBOList=mapper.convertToProjectBO(projectEntityList);
		return projectBOList;
	}
	@Override
	public ProjectBO createProject(ProjectBO projectBO) {
		ProjectMapper mapper=new ProjectMapper();
		ProjectEntity projectEntity=mapper.convertToEntity(projectBO);
		ProjectEntity savedProjectEntity =projectRepo.save(projectEntity);
		ProjectBO savedProjectBO=mapper.convertToResource(savedProjectEntity);
		return savedProjectBO;
	}

}
