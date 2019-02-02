package com.taskplan.service;

import java.util.List;

import com.taskplan.model.UserBO;


public interface IUserService {

	List<UserBO> findAllUsers();
	List<UserBO> findAllUnAssignedUsers();
	List<UserBO> findAllUnAssignedTaskUsers();
	UserBO createUser(UserBO userBO);
	UserBO updateUser(String userId,UserBO userBO);
	void deleteUser(String userId);
}
