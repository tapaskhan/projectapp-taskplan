package com.taskplan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.taskplan.controller.TaskController;
import com.taskplan.service.impl.TaskService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=TaskController.class)
public class TaskControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TaskService taskService;
	@Test
	public void testGetAllTasks() throws Exception {
		
	}
	@Test
	public void testCreateTask() throws Exception {
		
	}
	@Test
	public void testUpdateTask() throws Exception {
		
	}
}
