package com.taskplan;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.taskplan.controller.ProjectController;
import com.taskplan.model.ProjectBO;
import com.taskplan.service.impl.ProjectService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=ProjectController.class)
public class ProjectControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProjectService projectService;
	

	


	private ProjectBO createProjectBO() throws Exception{
		ProjectBO projectBO=new ProjectBO();
		
	    String startDateStr="2019-01-09";  
	    Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr); 
	    String endDateStr="2019-01-20";  
	    Date endDate=new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);     

	    projectBO.setStartDate(startDate);
		projectBO.setEndDate(endDate);		
		projectBO.setPriority(1);
		projectBO.setProjectDesc("test project");
		projectBO.setId(1);
		return projectBO;
	}

	@Test
	public void testGetAllProjects() throws Exception {
		List<ProjectBO> projectBOlist=new ArrayList<ProjectBO>();
		projectBOlist.add(createProjectBO());
		Mockito.when(projectService.findAllProjects()).thenReturn(projectBOlist);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/projectapp/projects").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		String expected = "[{\"id\":1,\"projectDesc\":\"test project\",\"priority\":1,\"startDate\":\"2019-01-08T18:30:00.000+0000\",\"endDate\":\"2019-01-19T18:30:00.000+0000\",\"user\":null,\"taskCount\":0,\"taskCompleted\":0}]";

		 
		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), true);
	}


	@Test
	public void testCreateProject() throws Exception{		
		ProjectBO projectBO=createProjectBO();
		Mockito.when(projectService.createProject(projectBO)).thenReturn(projectBO);

		String requestJson = "{\"id\":1,\"projectDesc\":\"test project\",\"priority\":1,\"startDate\":\"2019-01-08T18:30:00.000+0000\",\"endDate\":\"2019-01-19T18:30:00.000+0000\",\"user\":null,\"taskCount\":0,\"taskCompleted\":0}";
		mockMvc.perform(MockMvcRequestBuilders.post(
				"/projectapp/project").contentType(MediaType.APPLICATION_JSON)
		        .content(requestJson)
				.accept(MediaType.APPLICATION_JSON));
										
				
				

		
	}
	@Test
	public void testUpdateProject() throws Exception{
		
		ProjectBO projectBO=createProjectBO();
		Mockito.when(projectService.createProject(projectBO)).thenReturn(projectBO);

		String requestJson = "{\"id\":1,\"projectDesc\":\"test project\",\"priority\":1,\"startDate\":\"2019-01-08T18:30:00.000+0000\",\"endDate\":\"2019-01-19T18:30:00.000+0000\",\"user\":null,\"taskCount\":0,\"taskCompleted\":0}";
		mockMvc.perform(MockMvcRequestBuilders.put(
				"/projectapp/project/1").contentType(MediaType.APPLICATION_JSON)
		        .content(requestJson)
				.accept(MediaType.APPLICATION_JSON));
									
				
				

		
	
	}
}
