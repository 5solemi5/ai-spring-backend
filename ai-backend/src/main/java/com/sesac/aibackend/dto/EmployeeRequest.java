package com.sesac.aibackend.dto;

import com.sesac.aibackend.domain.Department;
import com.sesac.aibackend.domain.Employee;

public record EmployeeRequest(
        String name,
        Long departmentId
) {
    public Employee toEntity(Department department) {
        return Employee.builder()
                .name(name)
                .department(department)
                .build();
    }
}