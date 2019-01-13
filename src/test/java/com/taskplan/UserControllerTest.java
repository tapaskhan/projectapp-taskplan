package com.taskplan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.taskplan.controller.UserController;
import com.taskplan.service.impl.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=UserController.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	@Test
	public void testGetUsers() throws Exception {
		
	}
	@Test
	public void testCreateUser() throws Exception {
		
	}
	@Test
	public void testUpdateUser() throws Exception {
		
	}
	@Test
	public void testGetAllTaskUsers() throws Exception {
		
	}

}
