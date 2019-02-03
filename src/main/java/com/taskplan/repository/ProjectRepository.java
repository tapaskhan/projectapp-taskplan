package com.taskplan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taskplan.dao.entity.ProjectEntity;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long>{

	@Query(IQuery.PROJECT_FIND_ALL_ACTIVE)
	List<ProjectEntity> findAllActiveProjects();
}
