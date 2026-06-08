package com.sesac.aibackend.repository;

import com.sesac.aibackend.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}