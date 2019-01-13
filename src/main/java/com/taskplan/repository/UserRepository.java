package com.taskplan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taskplan.dao.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@Query(IQuery.USERS_FIND_BY_NULL_PROJECT_ID)
	List<UserEntity> findAllUnassignedUsers();
	@Query(IQuery.USERS_FIND_BY_NULL_TASK_ID)
	List<UserEntity> findAllUnassignedTaskUsers();
}
