package com.taskplan;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.taskplan.controller.UserController;
import com.taskplan.model.ProjectBO;
import com.taskplan.model.UserBO;
import com.taskplan.service.impl.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=UserController.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	private UserBO createUserBO(int id ,String firstName,String lastName,int employeeId) {
		UserBO userBO=new UserBO();				
		userBO.setFirstName(firstName);
		userBO.setId(id);
		userBO.setLastName(lastName);
		userBO.setEmployeeId(employeeId);
		return userBO;
		
	}
	@Test
	public void testGetUsers() throws Exception {

		List<UserBO> userBOList=new ArrayList<UserBO>();
		userBOList.add(createUserBO(6,"Test Another","User",1192));
		Mockito.when(userService.findAllUnAssignedUsers()).thenReturn(userBOList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/projectapp/users").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "[{\"id\":6,\"firstName\":\"Test Another\",\"lastName\":\"User\",\"employeeId\":1192,\"allowDelete\":false}]";		
		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), true);
	
		
	}
	@Test
	public void testCreateUser() throws Exception {		
		UserBO userBO=createUserBO(6,"Test Another","User",1192);
		Mockito.when(userService.createUser(userBO)).thenReturn(userBO);

		String requestJson = "{\r\n" + 
				"    \"id\": 6,\r\n" + 
				"    \"firstName\": \"Test Another\",\r\n" + 
				"    \"lastName\": \"User\",\r\n" + 
				"    \"employeeId\": 1192\r\n" + 
				"}";
		mockMvc.perform(MockMvcRequestBuilders.post(
				"/projectapp/user").contentType(MediaType.APPLICATION_JSON)
		        .content(requestJson)
				.accept(MediaType.APPLICATION_JSON));
				
	}
	@Test
	public void testUpdateUser() throws Exception {
		
		UserBO userBO=createUserBO(6,"Test Another","User",1192);
		Mockito.when(userService.updateUser("6", userBO)).thenReturn(userBO);

		String requestJson = "{\r\n" + 
				"    \"id\": 6,\r\n" + 
				"    \"firstName\": \"Test Another\",\r\n" + 
				"    \"lastName\": \"User\",\r\n" + 
				"    \"employeeId\": 1192\r\n" + 
				"}";
		mockMvc.perform(MockMvcRequestBuilders.put(
				"/projectapp/user/6").contentType(MediaType.APPLICATION_JSON)
		        .content(requestJson)
				.accept(MediaType.APPLICATION_JSON));
			
	
	}
	@Test
	public void testGetAllTaskUsers() throws Exception {


		List<UserBO> userBOList=new ArrayList<UserBO>();
		userBOList.add(createUserBO(6,"Test Another","User",1192));
		Mockito.when(userService.findAllUnAssignedTaskUsers()).thenReturn(userBOList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/projectapp/taskusers").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "[{\"id\":6,\"firstName\":\"Test Another\",\"lastName\":\"User\",\"employeeId\":1192,\"allowDelete\":false}]";		
		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), true);
	
		
	
	}
	@Test
	public void testGetAllUsers() throws Exception {

		List<UserBO> userBOList=new ArrayList<UserBO>();
		userBOList.add(createUserBO(6,"Test Another","User",1192));
		Mockito.when(userService.findAllUsers()).thenReturn(userBOList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/projectapp/allusers").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "[{\"id\":6,\"firstName\":\"Test Another\",\"lastName\":\"User\",\"employeeId\":1192,\"allowDelete\":false}]";		
		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), true);
	
		
	}
	@Test
	public void testDeleteUser() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.delete(
				"/projectapp/user/4").contentType(MediaType.APPLICATION_JSON)
		        				.accept(MediaType.APPLICATION_JSON));
								
;
			
	
	}

}
