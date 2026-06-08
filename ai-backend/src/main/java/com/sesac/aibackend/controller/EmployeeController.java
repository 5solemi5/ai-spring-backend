package com.sesac.aibackend.controller;

import com.sesac.aibackend.dto.EmployeeRequest;
import com.sesac.aibackend.dto.EmployeeResponse;
import com.sesac.aibackend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeResponse create(@RequestBody EmployeeRequest request) {
        return employeeService.create(request);
    }

    @GetMapping
    public List<EmployeeResponse> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponse update(
            @PathVariable Long id,
            @RequestBody EmployeeRequest request
    ) {
        return employeeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
}