package com.taskplan.repository;

public interface IQuery {

	//public String PROJECT_FINDALL="Select pe from ProjectEntity pe";
	public String TASK_FIND_BY_PROJECT_ID="Select te from TaskEntity te where te.projectEntity.id = ?1";
	public String USERS_FIND_BY_NULL_PROJECT_ID="Select ue from UserEntity ue where ue.projectEntity.id = null";
}
