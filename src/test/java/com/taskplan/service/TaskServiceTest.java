package com.taskplan.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.taskplan.dao.entity.ParentTaskEntity;
import com.taskplan.dao.entity.TaskEntity;
import com.taskplan.dao.entity.UserEntity;
import com.taskplan.model.ParentTaskBO;
import com.taskplan.model.TaskBO;
import com.taskplan.model.UserBO;
import com.taskplan.repository.ProjectRepository;
import com.taskplan.repository.TaskRepository;
import com.taskplan.repository.UserRepository;
import com.taskplan.service.impl.TaskService;
import com.taskplan.service.impl.UserService;
import com.taskplan.service.util.ProjectMapper;
import com.taskplan.service.util.TaskMapper;

@RunWith(SpringRunner.class)
public class TaskServiceTest {

	@Mock
	private ProjectRepository projectRepo;
	@Mock
	private TaskMapper mapper;
	
	@Mock
	private UserRepository userRepo;
	@Mock
	private TaskRepository taskRepo;
	@InjectMocks
	private TaskService taskService;
	
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
		taskEntity.setId(id);
		taskEntity.setPriority(priority);
		taskEntity.setTaskDesc(taskDesc);
		
		return taskEntity;
		
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
	public void testFindAllTasksByProjectId() throws Exception{
		//UserBO userBO=createUserBO(1,"First Name","LastName",111);
		TaskEntity taskEntity=createTaskEntity(1,"2019-01-09","2019-01-20",1,"Task Desc");
		TaskBO taskBO=new TaskMapper().convertToResource(taskEntity);
		List<TaskEntity> taskEntityList=new ArrayList<TaskEntity>();
		taskEntityList.add(taskEntity);
		
		when(mapper.convertToResource(taskEntity)).thenReturn(taskBO);
		when(mapper.convertToTaskBOList(taskEntityList)).thenCallRealMethod(); 
		when(taskRepo.findAllTasksByProjectId(new Long(1))).thenReturn(taskEntityList);
		
		
		List<TaskBO> savedTaskBOList=taskService.findAllTasksByProjectId("1");
		assertEquals(1, savedTaskBOList.size());
		assertEquals(taskEntity.getTaskDesc(), savedTaskBOList.get(0).getTaskDesc());
		assertEquals(taskEntity.getId(), savedTaskBOList.get(0).getId());
		assertEquals(taskEntity.getPriority(), savedTaskBOList.get(0).getPriority());
		
			
	}
	private ParentTaskEntity createParentTaskEntity(int id,String description) {
		ParentTaskEntity parentTaskEntity =new ParentTaskEntity();
		parentTaskEntity.setId(new Long(id));
		parentTaskEntity.setParentTaskDec(description);
		return parentTaskEntity;
	}
	@Test
	public void testFindAllParentTasksByProjectId() throws Exception{
		//UserBO userBO=createUserBO(1,"First Name","LastName",111);
		TaskEntity taskEntity=createTaskEntity(1,"2019-01-09","2019-01-20",1,null);
		ParentTaskEntity parentTaskEntity =createParentTaskEntity(1,"Parent Task");
		parentTaskEntity.addTaskEntity(taskEntity);
		taskEntity.setParentTaskEntity(parentTaskEntity);
		
		//TaskBO taskBO=new TaskMapper().convertToResource(taskEntity);
		List<TaskEntity> taskEntityList=new ArrayList<TaskEntity>();
		taskEntityList.add(taskEntity);
		
		//when(mapper.convertToResource(taskEntity)).thenReturn(taskBO);
		when(mapper.convertToParentTaskBOList(taskEntityList)).thenCallRealMethod(); 
		when(taskRepo.findAllTasksByProjectId(new Long(1))).thenReturn(taskEntityList);
		
		
		List<ParentTaskBO> savedParentTaskBOList=taskService.findAllParentTasksByProjectId("1");
		assertEquals(1, savedParentTaskBOList.size());
		assertEquals(parentTaskEntity.getParentTaskDec(), savedParentTaskBOList.get(0).getParentTaskDec());
		
		
			
	}
}
