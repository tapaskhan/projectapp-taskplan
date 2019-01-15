package com.taskplan.service.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.taskplan.dao.entity.ParentTaskEntity;
import com.taskplan.dao.entity.TaskEntity;
import com.taskplan.model.ParentTaskBO;
import com.taskplan.model.TaskBO;
@Component
public class TaskMapper extends BaseMapper<TaskBO, TaskEntity> {

	public List<TaskBO> convertToTaskBOList(List<TaskEntity> taskEntityList){
	
		List<TaskBO> taskBOList=null;
		if(taskEntityList!=null) {
			taskBOList=new ArrayList<TaskBO>();
			for(TaskEntity taskEntity:taskEntityList) {
				TaskBO taskBO=convertToResource(taskEntity);
				updateTaskBO(taskBO,taskEntity);
				taskBOList.add(taskBO);
			}
		}
		return taskBOList;
	}
	public List<ParentTaskBO> convertToParentTaskBOList(List<TaskEntity> taskEntityList){
		List<ParentTaskBO> parentTaskBOList=null;
		if(taskEntityList!=null) {
			parentTaskBOList=new ArrayList<ParentTaskBO>();
			for(TaskEntity taskEntity:taskEntityList) {
				if(taskEntity.getParentTaskEntity()!=null &&  taskEntity.getTaskDesc()==null) {
					ParentTaskBO parentTaskBO=convertToParentTaskBO(taskEntity.getParentTaskEntity());
					parentTaskBOList.add(parentTaskBO);
				}				
			}
		}
		return parentTaskBOList;
	}
	@Override
	public TaskBO convertToResource(TaskEntity taskEntity){	
		//TaskBO taskBO=super.convertToResource(taskEntity);
		TaskBO taskBO=mapToTaskBO(taskEntity);
		updateTaskBO(taskBO,taskEntity);		
		return taskBO;
	}
	private TaskBO mapToTaskBO(TaskEntity taskEntity) {
		TaskBO taskBO=new TaskBO();
		taskBO.setEndDate(taskEntity.getEndDate());
		taskBO.setId(taskEntity.getId());
		taskBO.setPriority(taskEntity.getPriority());
		taskBO.setStartDate(taskEntity.getStartDate());
		taskBO.setStatus(taskEntity.getStatus());
		taskBO.setTaskDesc(taskEntity.getTaskDesc());
		if(taskEntity.getTaskDesc()==null) {
			taskBO.setParentTask(true);
		}
		return taskBO;
	}
	private void updateTaskBO(TaskBO taskBO,TaskEntity taskEntity) {
		if(taskEntity.getParentTaskEntity()!=null) {					
			taskBO.setParentTaskDetails(convertToParentTaskBO(taskEntity.getParentTaskEntity()));
	    }
		if(taskEntity.getProjectEntity()!=null) {
				taskBO.setProject(new ProjectMapper().convertToResource(taskEntity.getProjectEntity()));
		}
		if(taskEntity.getUserEntity()!=null) {
		   		taskBO.setUser(new UserMapper().convertToResource(taskEntity.getUserEntity()));
		}
	}
	private ParentTaskBO convertToParentTaskBO(ParentTaskEntity parentTaskEntity) {
		ParentTaskBO parentTaskBO=new ParentTaskBO();
		parentTaskBO.setParentTaskDec(parentTaskEntity.getParentTaskDec());
		parentTaskBO.setId(parentTaskEntity.getId());
		return parentTaskBO;
	}
}
