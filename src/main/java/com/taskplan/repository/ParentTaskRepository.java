package com.taskplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taskplan.dao.entity.ParentTaskEntity;

@Repository
@Transactional
public interface ParentTaskRepository extends JpaRepository<ParentTaskEntity, Long> {

}
