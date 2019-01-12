package com.taskplan.model;

import java.util.List;

public class ParentTaskBO {

	private long id;
	private String parentTaskDec;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getParentTaskDec() {
		return parentTaskDec;
	}
	public void setParentTaskDec(String parentTaskDec) {
		this.parentTaskDec = parentTaskDec;
	}
	
}
