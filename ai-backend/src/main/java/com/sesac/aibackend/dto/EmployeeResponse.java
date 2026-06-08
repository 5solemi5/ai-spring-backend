package com.sesac.aibackend.dto;

import com.sesac.aibackend.domain.Employee;

public record EmployeeResponse(
        Long id,
        String name,
        Long departmentId,
        String departmentName
) {
    public static EmployeeResponse from(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getDepartment().getId(),
                employee.getDepartment().getName()
        );
    }
}