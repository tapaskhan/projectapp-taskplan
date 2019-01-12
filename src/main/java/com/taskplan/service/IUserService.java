package com.taskplan.service;

import java.util.List;

import com.taskplan.model.UserBO;


public interface IUserService {

	List<UserBO> findAllUnAssignedUsers();
	UserBO createUser(UserBO userBO);
	UserBO updateUser(String userId,UserBO userBO);
	
}
