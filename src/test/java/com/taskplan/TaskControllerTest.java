package com.taskplan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.taskplan.controller.TaskController;
import com.taskplan.dao.entity.TaskEntity;
import com.taskplan.model.ParentTaskBO;
import com.taskplan.model.TaskBO;
import com.taskplan.model.UserBO;
import com.taskplan.service.impl.TaskService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=TaskController.class)
public class TaskControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TaskService taskService;
	
	private TaskBO createTaskBO(int id ,String startDateStr,String endDateStr,int priority,String taskDesc) throws Exception {
		//String startDateStr="2019-01-09";  
	    Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr); 
	    //String endDateStr="2019-01-20";  
	    Date endDate=new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
	    TaskBO taskBO=new TaskBO();
	    taskBO.setStartDate(startDate);
	    taskBO.setEndDate(endDate);
		taskBO.setId(id);
		taskBO.setPriority(priority);
		taskBO.setTaskDesc(taskDesc);
		
		return taskBO;
		
	}
	@Test
	public void testGetAllTasks() throws Exception {


		List<TaskBO> taskBOList=new ArrayList<TaskBO>();
		taskBOList.add(createTaskBO(1,"2019-01-19","2019-01-26",2,"My first task"));
		Mockito.when(taskService.findAllTasksByProjectId("1")).thenReturn(taskBOList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/projectapp/tasks?projectId=1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "[{\"id\":1,\"taskDesc\":\"My first task\",\"priority\":2,\"startDate\":\"2019-01-18T18:30:00.000+0000\",\"endDate\":\"2019-01-25T18:30:00.000+0000\",\"status\":null,\"isParentTask\":false,\"project\":null,\"user\":null,\"parentTaskDetails\":null}]";		
		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), true);
	
		
	
	}
	@Test
	public void testCreateTask() throws Exception {
		
		TaskBO taskBO=createTaskBO(1,"2019-01-19","2019-01-26",2,"My first task");
		Mockito.when(taskService.createTasks(taskBO)).thenReturn(taskBO);

		String requestJson = "{\r\n" + 
				"	\"taskDesc\":\"My first task\",\r\n" + 
				"	\"startDate\":\"2019-01-19\",\r\n" + 
				"	\"endDate\":\"2019-01-26\",\r\n" + 
				"	\"priority\":\"2\",\r\n" + 
				"	\"status\":\"PENDING\",\r\n" + 
				"	\"project\":\r\n" + 
				"	{\r\n" + 
				"		\"id\":5\r\n" + 
				"	},\r\n" + 
				"	\"user\":\r\n" + 
				"	{\r\n" + 
				"		\"id\":1\r\n" + 
				"		\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"}";
		mockMvc.perform(MockMvcRequestBuilders.post(
				"/projectapp/task").contentType(MediaType.APPLICATION_JSON)
		        .content(requestJson)
				.accept(MediaType.APPLICATION_JSON));
				
	
		
	}
	@Test
	public void testUpdateTask() throws Exception {
		TaskBO taskBO=createTaskBO(1,"2019-01-19","2019-01-26",2,"My first task");
		Mockito.when(taskService.updateTask(taskBO,"1")).thenReturn(taskBO);

		String requestJson = "{\r\n" + 
				"	\"taskDesc\":\"My first task\",\r\n" + 
				"	\"startDate\":\"2019-01-19\",\r\n" + 
				"	\"endDate\":\"2019-01-26\",\r\n" + 
				"	\"priority\":\"2\",\r\n" + 
				"	\"status\":\"PENDING\",\r\n" + 
				"	\"project\":\r\n" + 
				"	{\r\n" + 
				"		\"id\":5\r\n" + 
				"	},\r\n" + 
				"	\"user\":\r\n" + 
				"	{\r\n" + 
				"		\"id\":1\r\n" + 
				"		\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"}";
		mockMvc.perform(MockMvcRequestBuilders.put(
				"/projectapp/task/1").contentType(MediaType.APPLICATION_JSON)
		        .content(requestJson)
				.accept(MediaType.APPLICATION_JSON));
	}
	@Test
	public void testParentTask() throws Exception {
		List<ParentTaskBO> parentTaskBOList=new ArrayList<ParentTaskBO>();
		ParentTaskBO parentTaskBO=new ParentTaskBO();
		parentTaskBO.setId(1);
		parentTaskBO.setParentTaskDec("Parent Description Added");
		parentTaskBOList.add(parentTaskBO);
		Mockito.when(taskService.findAllParentTasksByProjectId("1")).thenReturn(parentTaskBOList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/projectapp/parenttasks?projectId=1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "[{\"id\":1,\"parentTaskDec\":\"Parent Description Added\"}]";		
		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), true);
	
		
	
	
	}
	
}
