package com.taskplan.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="parent_task")
public class ParentTaskEntity implements Serializable {

	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="parent_id")
	    private long id;
	    

	    @Column(name="parent_task_desc")
	    private String parentTaskDec;
	    
	    @OneToMany(mappedBy="parentTaskEntity")
	    private List<TaskEntity> taskEntityList;

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

		public List<TaskEntity> getTaskEntityList() {
			return taskEntityList;
		}

		public void setTaskEntityList(List<TaskEntity> taskEntityList) {
			this.taskEntityList = taskEntityList;
		}
		
		public void addTaskEntity(TaskEntity taskEntity) {
		  if(this.taskEntityList ==null) {
			  this.taskEntityList=new ArrayList<TaskEntity>();
		  }
		  this.taskEntityList.add(taskEntity);
		}
}
