package com.taskplan.repository;

public interface IQuery {

	public String PROJECT_FIND_ALL_ACTIVE="Select pe from ProjectEntity pe where pe.inactive =false ";
	public String TASK_FIND_BY_PROJECT_ID="Select te from TaskEntity te where te.projectEntity.id = ?1";
	public String USERS_FIND_BY_NULL_PROJECT_ID="Select ue from UserEntity ue where ue.projectEntity.id = null";
	public String USERS_FIND_BY_NULL_TASK_ID="Select ue from UserEntity ue where ue.taskEntity.id = null";
}
