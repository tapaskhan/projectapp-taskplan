package com.taskplan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taskplan.dao.entity.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

	@Query(IQuery.TASK_FIND_BY_PROJECT_ID)
	List<TaskEntity> findAllTasksByProjectId(Long projectId);
}
