package com.taskplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Autowired
	private ProjectMapper mapper;
	
	@Override	
	public List<ProjectBO> findAllProjects() {
		List<ProjectEntity> projectEntityList= projectRepo.findAll();		
		List<ProjectBO> projectBOList=mapper.convertToProjectBO(projectEntityList);
		return projectBOList;
	}
	@Override	
	public List<ProjectBO> findAllActiveProjects() {
		List<ProjectEntity> projectEntityList= projectRepo.findAllActiveProjects();		
		List<ProjectBO> projectBOList=mapper.convertToProjectBO(projectEntityList);
		return projectBOList;
	}
	
	@Override	
	public ProjectBO createProject(ProjectBO projectBO) {		
		ProjectEntity projectEntity=mapper.convertToEntity(projectBO);		
		ProjectEntity savedProjectEntity =projectRepo.save(projectEntity);
		if(projectBO.getUser()!=null) {
			UserEntity userEntity=userRepo.getOne(new Long(projectBO.getUser().getId()));
			userEntity.setProjectEntity(savedProjectEntity);
			UserEntity savedUserEntity=userRepo.save(userEntity);
			savedProjectEntity.setUserEntity(savedUserEntity);
		}
		ProjectBO savedProjectBO=mapper.convertToResource(savedProjectEntity);
		return savedProjectBO;
	}
	@Override	
	public ProjectBO updateProject(String projectId,ProjectBO projectBO) {		
		ProjectEntity projectEntity=projectRepo.getOne(new Long(projectId));
		UserEntity previousUserEntity=projectEntity.getUserEntity();
		ProjectBO savedProjectBO=null;
		if(projectEntity!=null) {
			projectEntity.setEndDate(projectBO.getEndDate());
			projectEntity.setPriority(projectBO.getPriority());
			projectEntity.setProjectDesc(projectBO.getProjectDesc());
			projectEntity.setStartDate(projectBO.getStartDate());
						
			ProjectEntity savedProjectEntity =projectRepo.save(projectEntity);
			
			if(projectBO.getUser()!=null) {
				UserEntity userEntity=userRepo.getOne(new Long(projectBO.getUser().getId()));
				userEntity.setProjectEntity(savedProjectEntity);
				UserEntity savedUserEntity=userRepo.save(userEntity);
				savedUserEntity.setProjectEntity(savedProjectEntity);
				if(previousUserEntity!=null && previousUserEntity.getId()!=userEntity.getId()) {
					previousUserEntity.setProjectEntity(null);
					userRepo.save(previousUserEntity);
				}
				savedProjectBO=mapper.convertToResource(savedUserEntity.getProjectEntity());
				savedProjectBO.setUser(new UserMapper().convertToResource(savedUserEntity));
			}
			
		}
		return savedProjectBO;
	}
	@Override	
	public ProjectBO updateProjectStatus(String projectId,ProjectBO projectBO) {		
		ProjectEntity projectEntity=projectRepo.getOne(new Long(projectId));
		
		ProjectBO savedProjectBO=null;
		if(projectEntity!=null) {
			projectEntity.setInactive(projectBO.isInactive());			
			ProjectEntity savedProjectEntity =projectRepo.save(projectEntity);
			savedProjectBO=mapper.convertToResource(savedProjectEntity);			
			
		}
		return savedProjectBO;
	}
	

}
