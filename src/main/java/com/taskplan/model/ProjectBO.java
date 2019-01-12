package com.taskplan.model;

import java.util.Date;



public class ProjectBO {

    private long id;
    private String projectDesc; 
    private int priority; 
    private Date startDate;
    private Date endDate;
    private UserBO user;
    private int taskCount;
    private int taskCompleted;
    
	public int getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}
	public int getTaskCompleted() {
		return taskCompleted;
	}
	public void setTaskCompleted(int taskCompleted) {
		this.taskCompleted = taskCompleted;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
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
	public UserBO getUser() {
		return user;
	}
	public void setUser(UserBO user) {
		this.user = user;
	}

}
