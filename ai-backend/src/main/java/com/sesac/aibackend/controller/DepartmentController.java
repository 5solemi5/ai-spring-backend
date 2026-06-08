package com.sesac.aibackend.controller;

import com.sesac.aibackend.dto.DepartmentRequest;
import com.sesac.aibackend.dto.DepartmentResponse;
import com.sesac.aibackend.dto.EmployeeResponse;
import com.sesac.aibackend.service.DepartmentService;
import com.sesac.aibackend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    @PostMapping
    public DepartmentResponse create(@RequestBody DepartmentRequest request) {
        return departmentService.create(request);
    }

    @GetMapping
    public List<DepartmentResponse> findAll() {
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    public DepartmentResponse findById(@PathVariable Long id) {
        return departmentService.findById(id);
    }

    @PutMapping("/{id}")
    public DepartmentResponse update(
            @PathVariable Long id,
            @RequestBody DepartmentRequest request
    ) {
        return departmentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departmentService.delete(id);
    }

    @GetMapping("/{departmentId}/employees")
    public List<EmployeeResponse> findEmployeesByDepartment(
            @PathVariable Long departmentId
    ) {
        return employeeService.findByDepartment(departmentId);
    }
}