package com.taskplan.service;

import java.util.List;

import com.taskplan.model.ParentTaskBO;
import com.taskplan.model.TaskBO;

public interface ITaskService {
	List<TaskBO> findAllTasksByProjectId(String projectId);
	TaskBO createTasks(TaskBO taskBO);
	TaskBO updateTask(TaskBO taskBO,String taskid);
	List<ParentTaskBO> findAllParentTasksByProjectId(String projectId);

}
