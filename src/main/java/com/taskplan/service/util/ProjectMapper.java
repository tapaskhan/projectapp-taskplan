package com.taskplan.service.util;

import java.util.ArrayList;
import java.util.List;

import com.taskplan.dao.entity.ProjectEntity;
import com.taskplan.model.ProjectBO;

public class ProjectMapper extends BaseMapper<ProjectBO, ProjectEntity> {

	public List<ProjectBO> convertToProjectBO(List<ProjectEntity> projectEntityList){
		List<ProjectBO> projectBOList=null;
		if(projectEntityList!=null) {
			projectBOList=new ArrayList<ProjectBO>();
			for(ProjectEntity projectEntity:projectEntityList) {
				ProjectBO projectBO=convertToResource(projectEntity);
				projectBOList.add(projectBO);
			}
		}
		return projectBOList;
		
	}
}
