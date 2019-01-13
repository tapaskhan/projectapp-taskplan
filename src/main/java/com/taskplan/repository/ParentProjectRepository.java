package com.taskplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskplan.dao.entity.ParentTaskEntity;

@Repository
public interface ParentProjectRepository extends JpaRepository<ParentTaskEntity, Long> {

}