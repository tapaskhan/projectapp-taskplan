package com.taskplan.service;

import org.junit.runner.RunWith;
import org.junit.Test;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.taskplan.dao.entity.ProjectEntity;
import com.taskplan.dao.entity.TaskEntity;
import com.taskplan.dao.entity.UserEntity;
import com.taskplan.model.UserBO;
import com.taskplan.repository.UserRepository;
import com.taskplan.service.impl.UserService;
import com.taskplan.service.util.UserMapper;
@RunWith(SpringRunner.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository userRepo;
	@Mock
	private UserMapper mapper;
	@InjectMocks
	private UserService userService;
	
	private UserEntity createUserEntity(int id ,String firstName,String lastName,int employeeId) {
		UserEntity userEntity=new UserEntity();
		userEntity.setFirstName(firstName);
		userEntity.setId(id);
		userEntity.setLastName(lastName);
		userEntity.setEmployeeId(employeeId);
		return userEntity;
		
	}
	private UserBO createUserBO(int id ,String firstName,String lastName,int employeeId) {
		UserBO userBO=new UserBO();				
		userBO.setFirstName(firstName);
		userBO.setId(id);
		userBO.setLastName(lastName);
		userBO.setEmployeeId(employeeId);
		return userBO;
		
	}
	@Test
	public void testCreateUser() throws Exception{
		UserBO userBO=createUserBO(1,"First Name","LastName",111);
		UserEntity userEntity=createUserEntity(1,"First Name","LastName",111);
		when(mapper.convertToEntity(userBO)).thenReturn(userEntity);
		when(mapper.convertToResource(userEntity)).thenReturn(userBO);
		when(userRepo.save(userEntity)).thenReturn(userEntity);
		
		UserBO savedUserBO=userService.createUser(userBO);
		assertEquals("First Name", savedUserBO.getFirstName());
		assertEquals("LastName", savedUserBO.getLastName());
		assertEquals(new Integer(111), savedUserBO.getEmployeeId());
			
	}
	@Test
	public void testUpdateUser() throws Exception{
		UserBO userBO=createUserBO(1,"First Name","LastName",111);
		UserEntity userEntity=createUserEntity(1,"First Name","LastName",111);
		//when(mapper.convertToEntity(userBO)).thenReturn(userEntity);
		when(mapper.convertToResource(userEntity)).thenReturn(userBO);
		when(userRepo.save(userEntity)).thenReturn(userEntity);
		when(userRepo.getOne(new Long(1))).thenReturn(userEntity);
		
		UserBO savedUserBO=userService.updateUser("1", userBO);
		assertEquals("First Name", savedUserBO.getFirstName());
		assertEquals("LastName", savedUserBO.getLastName());
		assertEquals(new Integer(111), savedUserBO.getEmployeeId());
			
	}
	@Test
	public void testFindUnassignedUser() throws Exception{
		UserBO userBO=createUserBO(1,"First Name","LastName",111);
		UserEntity userEntity=createUserEntity(1,"First Name","LastName",111);
		List<UserEntity> userEntityList=new ArrayList<UserEntity>();
		userEntityList.add(userEntity);
		when(mapper.convertToResource(userEntity)).thenReturn(userBO);
		when(mapper.convertToUserBO(userEntityList)).thenCallRealMethod();
		when(userRepo.findAllUnassignedUsers()).thenReturn(userEntityList);
		
		List<UserBO> savedUserBOList=userService.findAllUnAssignedUsers();
		assertEquals(1, savedUserBOList.size());
		assertEquals("First Name", savedUserBOList.get(0).getFirstName());
		assertEquals("LastName", savedUserBOList.get(0).getLastName());
		assertEquals(new Integer(111), savedUserBOList.get(0).getEmployeeId());
			
	}
	@Test
	public void testFindUnassignedTaskUser() throws Exception{
		UserBO userBO=createUserBO(1,"First Name","LastName",111);
		UserEntity userEntity=createUserEntity(1,"First Name","LastName",111);
		List<UserEntity> userEntityList=new ArrayList<UserEntity>();
		userEntityList.add(userEntity);
		
		when(mapper.convertToResource(userEntity)).thenReturn(userBO);
		when(mapper.convertToUserBO(userEntityList)).thenCallRealMethod();
		when(userRepo.findAllUnassignedTaskUsers()).thenReturn(userEntityList);
		
		List<UserBO> savedUserBOList=userService.findAllUnAssignedTaskUsers();
		assertEquals(1, savedUserBOList.size());
		assertEquals("First Name", savedUserBOList.get(0).getFirstName());
		assertEquals("LastName", savedUserBOList.get(0).getLastName());
		assertEquals(new Integer(111), savedUserBOList.get(0).getEmployeeId());
			
	}
	@Test
	public void testFindAllUsers() throws Exception{		
		UserEntity userEntity=createUserEntity(1,"First Name","LastName",111);
		UserEntity userEntity1=createUserEntity(2,"Second Name","Second LastName",112);
		
		userEntity.setProjectEntity(new ProjectEntity());
		userEntity.setTaskEntity(new TaskEntity());
		UserBO userBO=new UserMapper().convertToResource(userEntity);		
		UserBO userBO1=new UserMapper().convertToResource(userEntity1);
		List<UserEntity> userEntityList=new ArrayList<UserEntity>();
		userEntityList.add(userEntity);
		userEntityList.add(userEntity1);
		when(mapper.convertToResource(userEntity)).thenReturn(userBO);
		when(mapper.convertToResource(userEntity1)).thenReturn(userBO1);
		when(mapper.convertToUserBO(userEntityList)).thenCallRealMethod();
		when(userRepo.findAll()).thenReturn(userEntityList);
		
		List<UserBO> savedUserBOList=userService.findAllUsers();
		assertEquals(2, savedUserBOList.size());
		assertEquals("First Name", savedUserBOList.get(0).getFirstName());
		assertEquals("LastName", savedUserBOList.get(0).getLastName());
		assertEquals(new Integer(111), savedUserBOList.get(0).getEmployeeId());
		assertEquals(false, savedUserBOList.get(0).isAllowDelete());
		assertEquals("Second Name", savedUserBOList.get(1).getFirstName());
		assertEquals("Second LastName", savedUserBOList.get(1).getLastName());
		assertEquals(new Integer(112), savedUserBOList.get(1).getEmployeeId());
		assertEquals(true, savedUserBOList.get(1).isAllowDelete());
			
	}
	@Test
	public void testDeleteUser() {
		UserBO userBO=createUserBO(1,"First Name","LastName",111);
		userService.deleteUser("1");
		verify(userRepo, times(1)).deleteById(new Long(1));

	}

}
