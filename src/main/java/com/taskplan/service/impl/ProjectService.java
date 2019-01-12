package com.taskplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskplan.dao.entity.ProjectEntity;
import com.taskplan.dao.entity.UserEntity;
import com.taskplan.model.ProjectBO;
import com.taskplan.repository.ProjectRepository;
import com.taskplan.repository.UserRepository;
import com.taskplan.service.IProjectService;
import com.taskplan.service.util.ProjectMapper;
import com.taskplan.service.util.UserMapper;
@Service("projectService")
public class ProjectService implements IProjectService {

	@Autowired
	private ProjectRepository projectRepo;
	@Autowired
	private UserRepository userRepo;
	
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
		if(projectBO.getUser()!=null) {
			UserEntity userEntity=userRepo.getOne(new Long(projectBO.getUser().getId()));
			projectEntity.setUserEntity(userEntity);
		}
		ProjectEntity savedProjectEntity =projectRepo.save(projectEntity);
		ProjectBO savedProjectBO=mapper.convertToResource(savedProjectEntity);
		return savedProjectBO;
	}
	@Override
	public ProjectBO updateProject(String projectId,ProjectBO projectBO) {
		ProjectMapper mapper=new ProjectMapper();
		ProjectEntity projectEntity=projectRepo.getOne(new Long(projectId));
		ProjectBO savedProjectBO=null;
		if(projectEntity!=null) {
			projectEntity.setEndDate(projectBO.getEndDate());
			projectEntity.setPriority(projectBO.getPriority());
			projectEntity.setProjectDesc(projectBO.getProjectDesc());
			projectEntity.setStartDate(projectBO.getStartDate());
						
			//ProjectEntity savedProjectEntity =projectRepo.save(projectEntity);
			if(projectBO.getUser()!=null) {
				UserEntity userEntity=userRepo.getOne(new Long(projectBO.getUser().getId()));
				userEntity.setProjectEntity(projectEntity);
				UserEntity savedUserEntity=userRepo.save(userEntity);
				savedProjectBO=mapper.convertToResource(savedUserEntity.getProjectEntity());
				savedProjectBO.setUser(new UserMapper().convertToResource(savedUserEntity));
			}
			
		}
		return savedProjectBO;
	}

}
