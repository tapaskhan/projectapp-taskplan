package com.taskplan.service.util;

import java.util.ArrayList;
import java.util.List;

import com.taskplan.dao.entity.ProjectEntity;
import com.taskplan.model.ProjectBO;
import com.taskplan.model.TaskBO;

public class ProjectMapper extends BaseMapper<ProjectBO, ProjectEntity> {

	public List<ProjectBO> convertToProjectBO(List<ProjectEntity> projectEntityList){
		List<ProjectBO> projectBOList=null;
		if(projectEntityList!=null) {
			projectBOList=new ArrayList<ProjectBO>();
			for(ProjectEntity projectEntity:projectEntityList) {
				ProjectBO projectBO=convertToResource(projectEntity);
				List<TaskBO> taskBOList=new TaskMapper().convertToTaskBOList(projectEntity.getTaskEntityList());
				populateTaskCountStatus(taskBOList,projectBO);
				projectBOList.add(projectBO);
			}
		}
		return projectBOList;
		
	}
	private void populateTaskCountStatus(List<TaskBO> taskBOList,ProjectBO projectBO) {
		if(taskBOList!=null) {
			projectBO.setTaskCount(taskBOList.size());
			int completeCount=0;
			for(TaskBO eachTaskBO:taskBOList) {
				if(StatusEnum.COMPLETE== eachTaskBO.getStatus()) {
					completeCount++;
				}
			}
			projectBO.setTaskCompleted(completeCount);
		}
		
	}
	 
}
