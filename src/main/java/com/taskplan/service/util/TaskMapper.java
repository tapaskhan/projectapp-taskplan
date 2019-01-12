package com.taskplan.service.util;

import java.util.ArrayList;
import java.util.List;

import com.taskplan.dao.entity.ParentTaskEntity;
import com.taskplan.dao.entity.TaskEntity;
import com.taskplan.model.ParentTaskBO;
import com.taskplan.model.TaskBO;

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
		TaskBO taskBO=super.convertToResource(taskEntity);
		updateTaskBO(taskBO,taskEntity);		
		return taskBO;
	}
	private void updateTaskBO(TaskBO taskBO,TaskEntity taskEntity) {
		if(taskEntity.getParentTaskEntity()!=null) {					
			taskBO.setParentTask(convertToParentTaskBO(taskEntity.getParentTaskEntity()));
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
