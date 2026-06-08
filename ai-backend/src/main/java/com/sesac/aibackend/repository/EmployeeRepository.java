package com.sesac.aibackend.repository;

import com.sesac.aibackend.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentId(Long departmentId);

    @Query("""
            select e from Employee e
            join fetch e.department
            where e.department.id = :departmentId
            order by e.id asc
            """)
    List<Employee> findByDepartmentIdWithDepartment(Long departmentId);

    @Query("""
            select e from Employee e
            join fetch e.department
            where e.id = :id
            """)
    Employee findByIdWithDepartment(Long id);
}