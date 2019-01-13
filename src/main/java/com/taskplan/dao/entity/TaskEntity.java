package com.taskplan.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.taskplan.service.util.StatusEnum;

@Entity
@Table(name="task")
public class TaskEntity implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_id")
    private long id;
 	 
 	@Column(name="task_desc")
    private String taskDesc;
 	
 	 @Column(name="priority")
     private int priority;
     
     @Column(name="start_date")
     @Temporal(TemporalType.DATE)
     private Date startDate;
     
     @Column(name="end_date")
     @Temporal(TemporalType.DATE)
     private Date endDate;
     
     @Column(name="status")
     @Enumerated(EnumType.STRING)
     private StatusEnum status;
     
    public void setStatus(StatusEnum status) {
		this.status = status;
	}

	@ManyToOne(cascade=CascadeType.ALL)
  	@JoinColumn(name="parent_task_id")
  	private ParentTaskEntity parentTaskEntity;
  	
  	@ManyToOne(cascade=CascadeType.ALL)
  	@JoinColumn(name="project_id")
  	private ProjectEntity projectEntity;
  	
  	@OneToOne(mappedBy="taskEntity",cascade=CascadeType.ALL)	   
    private UserEntity userEntity;
    

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

	

	public ParentTaskEntity getParentTaskEntity() {
		return parentTaskEntity;
	}

	public void setParentTaskEntity(ParentTaskEntity parentTaskEntity) {
		this.parentTaskEntity = parentTaskEntity;
	}

	public ProjectEntity getProjectEntity() {
		return projectEntity;
	}

	public void setProjectEntity(ProjectEntity projectEntity) {
		this.projectEntity = projectEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public StatusEnum getStatus() {
		return status;
	}
}
