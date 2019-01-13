package com.taskplan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskplan.model.ParentTaskBO;
import com.taskplan.model.TaskBO;
import com.taskplan.service.ITaskService;
import com.taskplan.service.impl.TaskService;

@RestController
public class TaskController {

	@Autowired
	private ITaskService taskService;
	
	@RequestMapping(value="/projectapp/tasks",method=RequestMethod.GET)
	public ResponseEntity<List<TaskBO>> getAllTasks(@RequestParam String projectId){
		List<TaskBO> taskBOlist=taskService.findAllTasksByProjectId(projectId);
		return new ResponseEntity<List<TaskBO>>(taskBOlist,HttpStatus.OK);
	}
	@RequestMapping(value="/projectapp/parenttasks",method=RequestMethod.GET)
	public ResponseEntity<List<ParentTaskBO>> getParentTasks(@RequestParam String projectId){
		List<ParentTaskBO> parentTaskBOlist=taskService.findAllParentTasksByProjectId(projectId);
		return new ResponseEntity<List<ParentTaskBO>>(parentTaskBOlist,HttpStatus.OK);
	}
	@RequestMapping(value="/projectapp/task",method=RequestMethod.POST)
	public ResponseEntity<TaskBO> createTask(@RequestBody TaskBO taskBO){
		TaskBO savedTaskBO=taskService.createTasks(taskBO);
		return new ResponseEntity<TaskBO>(savedTaskBO,HttpStatus.OK);
	}
	@RequestMapping(value="/projectapp/task/{id}",method=RequestMethod.PUT)
	public ResponseEntity<TaskBO> updateTask(@PathVariable("id") String taskId,@RequestBody TaskBO taskBO){
		TaskBO savedTaskBO=taskService.updateTask(taskBO, taskId);
		return new ResponseEntity<TaskBO>(savedTaskBO,HttpStatus.OK);
	}
}
