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
import com.taskplan.model.ProjectBO;
import com.taskplan.model.UserBO;
import com.taskplan.repository.ProjectRepository;
import com.taskplan.repository.UserRepository;
import com.taskplan.service.impl.ProjectService;
import com.taskplan.service.util.ProjectMapper;
import com.taskplan.service.util.StatusEnum;

@RunWith(SpringRunner.class)
public class ProjectServiceTest {

	@Mock
	private ProjectRepository projectRepo;
	@Mock
	private ProjectMapper mapper;
	
	@Mock
	private UserRepository userRepo;
	@InjectMocks
	private ProjectService projectService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	private ProjectEntity createProjectEntity(String startDateStr,String endDateStr,String desc,int id,int priority) throws Exception{
		ProjectEntity projectEntity=new ProjectEntity();
		
	    //String startDateStr="2019-01-09";  
	    Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr); 
	    //String endDateStr="2019-01-20";  
	    Date endDate=new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);     

	    projectEntity.setStartDate(startDate);
	    projectEntity.setEndDate(endDate);		
	    projectEntity.setPriority(priority);
	    projectEntity.setProjectDesc(desc);
	    projectEntity.setId(id);
		return projectEntity;
	}
	private ProjectBO createProjectBO(String startDateStr,String endDateStr,String desc,int id,int priority) throws Exception{
		ProjectBO projectBO=new ProjectBO();
		
	    //String startDateStr="2019-01-09";  
	    Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr); 
	    //String endDateStr="2019-01-20";  
	    Date endDate=new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);     

	    projectBO.setStartDate(startDate);
	    projectBO.setEndDate(endDate);		
	    projectBO.setPriority(priority);
	    projectBO.setProjectDesc(desc);
	    projectBO.setId(id);
		return projectBO;
	}
	private UserEntity createUserEntity(int id ,String firstName,String lastName,int employeeId) {
		UserEntity userEntity=new UserEntity();
		userEntity.setFirstName(firstName);
		userEntity.setId(id);
		userEntity.setLastName(lastName);
		userEntity.setEmployeeId(employeeId);
		return userEntity;
		
	}
	private TaskEntity createTaskEntity(int id ,String startDateStr,String endDateStr,int priority,String taskDesc) throws Exception {
		//String startDateStr="2019-01-09";  
	    Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr); 
	    //String endDateStr="2019-01-20";  
	    Date endDate=new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
		TaskEntity taskEntity=new TaskEntity();
		taskEntity.setStartDate(startDate);
		taskEntity.setEndDate(endDate);
		taskEntity.setStatus(StatusEnum.COMPLETE);
		taskEntity.setId(id);
		taskEntity.setPriority(priority);
		taskEntity.setTaskDesc(taskDesc);
		
		return taskEntity;
		
	}
	@Test
	public void testFindAllProjects() throws Exception{
		List<ProjectEntity> projectEntityList = new ArrayList<ProjectEntity>();
		List<TaskEntity> taskEntityList = new ArrayList<TaskEntity>();
		TaskEntity taskEntity=createTaskEntity(1,"2019-01-09","2019-01-20",1,"Task Desc");
		ProjectEntity projectEntity=createProjectEntity("2019-01-09","2019-01-20","Test Project",1,1);
		taskEntityList.add(taskEntity);
		projectEntity.setTaskEntityList(taskEntityList);
		ProjectEntity projectEntity1=createProjectEntity("2019-01-09","2019-01-20","Test Project 1",2,1);
		
		projectEntityList.add(projectEntity);
		projectEntityList.add(projectEntity1);
		List<ProjectBO> projectBOList=new ProjectMapper().convertToProjectBO(projectEntityList);
		when(projectRepo.findAll()).thenReturn(projectEntityList);
		when(mapper.convertToProjectBO(projectEntityList)).thenReturn(projectBOList);
			
		List<ProjectBO> result = projectService.findAllProjects();
		assertEquals(2, result.size());
		assertEquals(1, result.get(0).getTaskCompleted());
		assertEquals(1, result.get(0).getTaskCount());
	}
	@Test
	public void testCreateProject() throws Exception{		
		UserEntity userEntity=createUserEntity(1,"First Name","LastName",111);
		ProjectBO projectBO=createProjectBO("2019-01-09","2019-01-20","Test Project",1,1);
		ProjectEntity projectEntity=createProjectEntity("2019-01-09","2019-01-20","Test Project",1,1);
		//ProjectEntity projectEntity=projectMapper.convertToEntity(projectBO);
		UserBO userBO=new UserBO();
		userBO.setId(1);
		projectBO.setUser(userBO);
		
		when(mapper.convertToEntity(projectBO)).thenReturn(projectEntity);
		when(mapper.convertToResource(projectEntity)).thenReturn(projectBO);
		when(projectRepo.save(projectEntity)).thenReturn(projectEntity);
		when(userRepo.getOne(new Long(1))).thenReturn(userEntity);
		when(userRepo.save(userEntity)).thenReturn(userEntity);
		
		
		ProjectBO savedProjectBO = projectService.createProject(projectBO);
		assertEquals("Test Project", savedProjectBO.getProjectDesc());		
		assertEquals(1, savedProjectBO.getId());
		assertEquals(1, savedProjectBO.getPriority());		
		assertEquals(0, savedProjectBO.getTaskCount());
		assertEquals(0, savedProjectBO.getTaskCompleted());
	}
	@Test
	public void testUpdateProject() throws Exception{	
		UserEntity userEntity=createUserEntity(1,"First Name","LastName",111);
		UserEntity previousUserEntity=createUserEntity(2,"Another First Name","Another LastName",111);
		ProjectBO projectBO=createProjectBO("2019-01-09","2019-01-20","Test Project",1,1);
		ProjectEntity projectEntity=createProjectEntity("2019-01-09","2019-01-20","Test Project",1,1);
		projectEntity.setUserEntity(previousUserEntity);
		UserBO userBO=new UserBO();
		userBO.setId(1);
		projectBO.setUser(userBO);
		//when(projectMapper.convertToEntity(projectBO)).thenReturn(projectEntity);
		when(projectRepo.getOne(new Long(1))).thenReturn(projectEntity);
		when(projectRepo.save(projectEntity)).thenReturn(projectEntity);
		when(userRepo.getOne(new Long(1))).thenReturn(userEntity);
		when(userRepo.save(userEntity)).thenReturn(userEntity);
		when(userRepo.save(previousUserEntity)).thenReturn(previousUserEntity);
		when(mapper.convertToResource(projectEntity)).thenReturn(projectBO);
		
		
		ProjectBO savedProjectBO = projectService.updateProject("1", projectBO);
		assertEquals("Test Project", savedProjectBO.getProjectDesc());
		
		assertEquals(1, savedProjectBO.getId());
		assertEquals(1, savedProjectBO.getPriority());		
		assertEquals(0, savedProjectBO.getTaskCount());
		assertEquals(0, savedProjectBO.getTaskCompleted());
		assertEquals("First Name",savedProjectBO.getUser().getFirstName());
		assertEquals("LastName",savedProjectBO.getUser().getLastName());
		assertEquals(new Integer(111),savedProjectBO.getUser().getEmployeeId());
	}
}
