package com.taskplan.model;



public class UserBO {

	
    private long id;	
    private String firstName;
 	private String lastName;
 	private Integer employeeId;
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
	public boolean isAllowDelete() {
		return allowDelete;
	}
	public void setAllowDelete(boolean allowDelete) {
		this.allowDelete = allowDelete;
	}
	
}
