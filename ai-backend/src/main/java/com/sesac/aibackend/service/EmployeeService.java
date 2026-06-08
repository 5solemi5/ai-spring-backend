package com.sesac.aibackend.service;

import com.sesac.aibackend.domain.Department;
import com.sesac.aibackend.domain.Employee;
import com.sesac.aibackend.dto.EmployeeRequest;
import com.sesac.aibackend.dto.EmployeeResponse;
import com.sesac.aibackend.repository.DepartmentRepository;
import com.sesac.aibackend.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeResponse create(EmployeeRequest request) {
        Department department = departmentRepository.findById(request.departmentId())
                .orElseThrow(() -> new IllegalArgumentException("부서를 찾을 수 없습니다."));

        Employee employee = request.toEntity(department);

        return EmployeeResponse.from(employeeRepository.save(employee));
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public EmployeeResponse findById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("직원을 찾을 수 없습니다."));

        return EmployeeResponse.from(employee);
    }

    public EmployeeResponse update(Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("직원을 찾을 수 없습니다."));

        Department department = departmentRepository.findById(request.departmentId())
                .orElseThrow(() -> new IllegalArgumentException("부서를 찾을 수 없습니다."));

        employee.update(request.name(), department);

        return EmployeeResponse.from(employee);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> findByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentIdWithDepartment(departmentId)
                .stream()
                .map(EmployeeResponse::from)
                .toList();
    }
}