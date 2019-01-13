package com.taskplan.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taskplan.service.util.StatusEnum;



public class TaskBO {

   private long id; 	 
   private String taskDesc;	 	
   private int priority;	     
   private Date startDate;	     
   private Date endDate;     
   private StatusEnum status;
   @JsonProperty
   private boolean isParentTask;
   private ProjectBO project;
   private UserBO user;
   private ParentTaskBO parentTaskDetails;
   
public boolean getIsParentTask() {
	return isParentTask;
}

public void setParentTask(boolean isParentTask) {
	this.isParentTask = isParentTask;
}





public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getTaskDesc() {
	return taskDesc;
}

public void setTaskDesc(String taskDesc) {
	this.taskDesc = taskDesc;
}

public int getPriority() {
	return priority;
}

public void setPriority(int priority) {
	this.priority = priority;
}

public Date getStartDate() {
	return startDate;
}

public void setStartDate(Date startDate) {
	this.startDate = startDate;
}

public Date getEndDate() {
	return endDate;
}

public void setEndDate(Date endDate) {
	this.endDate = endDate;
}



public StatusEnum getStatus() {
	return status;
}

public void setStatus(StatusEnum status) {
	this.status = status;
}

public ProjectBO getProject() {
	return project;
}

public void setProject(ProjectBO project) {
	this.project = project;
}

public UserBO getUser() {
	return user;
}

public void setUser(UserBO user) {
	this.user = user;
}

public ParentTaskBO getParentTaskDetails() {
	return parentTaskDetails;
}

public void setParentTaskDetails(ParentTaskBO parentTaskDetails) {
	this.parentTaskDetails = parentTaskDetails;
}






}
