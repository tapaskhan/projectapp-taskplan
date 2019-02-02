package com.taskplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskplan.dao.entity.UserEntity;
import com.taskplan.model.UserBO;
import com.taskplan.repository.UserRepository;
import com.taskplan.service.IUserService;
import com.taskplan.service.util.UserMapper;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserMapper mapper;
	@Override
	public List<UserBO> findAllUsers() {
		List<UserEntity> userEntityList= userRepo.findAll();		
		List<UserBO> userBOList=mapper.convertToUserBO(userEntityList);
		return userBOList;
	}
	@Override
	public List<UserBO> findAllUnAssignedUsers() {
		List<UserEntity> userEntityList= userRepo.findAllUnassignedUsers();		
		List<UserBO> userBOList=mapper.convertToUserBO(userEntityList);
		return userBOList;
	}
	@Override
	public List<UserBO> findAllUnAssignedTaskUsers() {
		List<UserEntity> userEntityList= userRepo.findAllUnassignedTaskUsers();
		List<UserBO> userBOList=mapper.convertToUserBO(userEntityList);
		return userBOList;
	}
	@Override
	public UserBO createUser(UserBO userBO) {		
		UserEntity userEntity=mapper.convertToEntity(userBO);
		UserEntity savedUserEntity =userRepo.save(userEntity);
		UserBO savedUserBO=mapper.convertToResource(savedUserEntity);
		return savedUserBO;
	}
	@Override
	public UserBO updateUser(String userId, UserBO userBO) {		
		UserBO savedUserBO=null;
		UserEntity userEntity=userRepo.getOne(new Long(userId));
		if(userEntity!=null) {
			userEntity.setEmployeeId(userBO.getEmployeeId());
			userEntity.setFirstName(userBO.getFirstName());
			userEntity.setLastName(userBO.getLastName());
			UserEntity savedUserEntity =userRepo.save(userEntity);
			savedUserBO=mapper.convertToResource(savedUserEntity);
		}
		return savedUserBO;
	}
	@Override
	public void deleteUser(String userId) {
		userRepo.deleteById(new Long(userId));		
	}
	
}
