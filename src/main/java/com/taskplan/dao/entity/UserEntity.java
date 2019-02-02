package com.taskplan.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="users")
public class UserEntity implements Serializable{


 	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private long id;
 	 
 	@Column(name="first_name")
    private String firstName;
 	
 	@Column(name="last_name")
    private String lastName;
 	 
 	@Column(name="employee_id")
    private Integer employeeId;
 	
	@OneToOne
	@JoinColumn(name="project_id")
	private ProjectEntity projectEntity;
	
	@OneToOne
	@JoinColumn(name="task_id")
	private TaskEntity taskEntity;

	@Transient
	private boolean allowDelete;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public ProjectEntity getProjectEntity() {
		return projectEntity;
	}

	public void setProjectEntity(ProjectEntity projectEntity) {
		this.projectEntity = projectEntity;
	}

	public TaskEntity getTaskEntity() {
		return taskEntity;
	}

	public void setTaskEntity(TaskEntity taskEntity) {
		this.taskEntity = taskEntity;
	}

	public boolean isAllowDelete() {
		if(projectEntity ==null && taskEntity==null) {
			this.allowDelete= true;
		}else {
			this.allowDelete= false;
		}
		return allowDelete;
	}	

 	
}
