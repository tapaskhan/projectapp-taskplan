package com.taskplan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taskplan.model.UserBO;
import com.taskplan.service.impl.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/projectapp/users",method=RequestMethod.GET)
	public ResponseEntity<List<UserBO>> getAllUsers(){
		List<UserBO> userBOlist=userService.findAllUnAssignedUsers();
		return new ResponseEntity<List<UserBO>>(userBOlist,HttpStatus.OK);
	}
	@RequestMapping(value="/projectapp/user",method=RequestMethod.POST)
	public ResponseEntity<UserBO> createUser(@RequestBody UserBO userBO){
		UserBO savedUserBO=userService.createUser(userBO);
		return new ResponseEntity<UserBO>(savedUserBO,HttpStatus.OK);
	}
	@RequestMapping(value="/projectapp/user/{id}",method=RequestMethod.PUT)
	public ResponseEntity<UserBO> updateUser(@PathVariable("id") String userId,@RequestBody UserBO userBO){
		UserBO savedUserBO=userService.updateUser(userId,userBO);
		return new ResponseEntity<UserBO>(savedUserBO,HttpStatus.OK);
	}
}
