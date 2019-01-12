package com.taskplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskplan.dao.entity.ParentTaskEntity;
import com.taskplan.dao.entity.ProjectEntity;
import com.taskplan.dao.entity.TaskEntity;
import com.taskplan.dao.entity.UserEntity;
import com.taskplan.model.ParentTaskBO;
import com.taskplan.model.TaskBO;
import com.taskplan.repository.ParentProjectRepository;
import com.taskplan.repository.ProjectRepository;
import com.taskplan.repository.TaskRepository;
import com.taskplan.repository.UserRepository;
import com.taskplan.service.ITaskService;
import com.taskplan.service.util.TaskMapper;

@Service("taskService")
public class TaskService implements ITaskService{

	@Autowired
	private TaskRepository taskRepo;
	@Autowired
	private ProjectRepository projectRepo;
	@Autowired
	private ParentProjectRepository parentTaskRepo;
	@Autowired
	private UserRepository userRepo;
	@Override
	public List<TaskBO> findAllTasksByProjectId(String projectId) {
		List<TaskEntity> taskEntityList= taskRepo.findAllTasksByProjectId(new Long(projectId));
		TaskMapper mapper=new TaskMapper();
		List<TaskBO> taskBOList=mapper.convertToTaskBOList(taskEntityList);
		return taskBOList;
	}
	@Override
	public List<ParentTaskBO> findAllParentTasksByProjectId(String projectId) {
		List<TaskEntity> taskEntityList= taskRepo.findAllTasksByProjectId(new Long(projectId));
		TaskMapper mapper=new TaskMapper();
		List<ParentTaskBO> parentTaskBOList=mapper.convertToParentTaskBOList(taskEntityList);
		return parentTaskBOList;
	}

	@Override
	public TaskBO createTasks(TaskBO taskBO) {
		TaskMapper mapper=new TaskMapper();
		TaskBO savedTaskBO=null;
		if(taskBO.getProject()!=null) {
			ProjectEntity projectEntity=projectRepo.getOne(new Long(taskBO.getProject().getId()));
			UserEntity userEntity=null;
			if(taskBO.getUser()!=null) {
				userEntity=userRepo.getOne(taskBO.getUser().getId());
			}
			if(taskBO.isParentTask()) {
				ParentTaskEntity parentTaskEntity=new ParentTaskEntity();
				parentTaskEntity.setParentTaskDec(taskBO.getParentTask().getParentTaskDec());
				TaskEntity taskEntity=new TaskEntity(); 
				taskEntity.setProjectEntity(projectEntity);
				taskEntity.setUserEntity(userEntity);
				parentTaskEntity.addTaskEntity(taskEntity);
				ParentTaskEntity savedParentTaskEntity=parentTaskRepo.save(parentTaskEntity);
				TaskEntity savedTaskEntity=savedParentTaskEntity.getTaskEntityList().get(0);
				savedTaskBO=mapper.convertToResource(savedTaskEntity);
			}else {
				ParentTaskEntity parentTaskEntity=null;
				if(taskBO.getParentTask()!=null) {
					parentTaskEntity =parentTaskRepo.getOne(taskBO.getParentTask().getId());
				}
				TaskEntity taskEntity=mapper.convertToEntity(taskBO);
				taskEntity.setParentTaskEntity(parentTaskEntity);
				taskEntity.setUserEntity(userEntity);
				taskEntity.setProjectEntity(projectEntity);
				TaskEntity savedTaskEntity =taskRepo.save(taskEntity);
				savedTaskBO=mapper.convertToResource(savedTaskEntity);
			}
		}
		return savedTaskBO;
	}

	@Override
	public TaskBO updateTask(TaskBO taskBO,String taskid) {
		if(taskBO==null) {
			return null;
		}

		TaskMapper mapper=new TaskMapper();
		TaskEntity taskEntity=taskRepo.getOne(new Long(taskid));
		TaskBO savedTaskBO=null;
		ParentTaskEntity parentTaskEntity=null;
		if(taskBO.getParentTask()!=null) {
			parentTaskEntity =parentTaskRepo.getOne(taskBO.getParentTask().getId());
		}
		UserEntity userEntity=null;
		if(taskBO.getUser()!=null) {
			userEntity=userRepo.getOne(taskBO.getUser().getId());
		}
		if(taskEntity!=null) {
			taskEntity.setEndDate(taskBO.getEndDate());
			taskEntity.setPriority(taskBO.getPriority());
			taskEntity.setTaskDesc(taskBO.getTaskDesc());
			taskEntity.setStartDate(taskBO.getStartDate());
			taskEntity.setParentTaskEntity(parentTaskEntity);
			taskEntity.setUserEntity(userEntity);
			TaskEntity savedTaskEntity =taskRepo.save(taskEntity);
			savedTaskBO=mapper.convertToResource(savedTaskEntity);
			
		}		
	  return savedTaskBO; 
	}

	
}
