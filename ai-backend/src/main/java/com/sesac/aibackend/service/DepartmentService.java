package com.sesac.aibackend.service;

import com.sesac.aibackend.domain.Department;
import com.sesac.aibackend.dto.DepartmentRequest;
import com.sesac.aibackend.dto.DepartmentResponse;
import com.sesac.aibackend.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentResponse create(DepartmentRequest request) {
        Department department = request.toEntity();
        return DepartmentResponse.from(departmentRepository.save(department));
    }

    @Transactional(readOnly = true)
    public List<DepartmentResponse> findAll() {
        return departmentRepository.findAll()
                .stream()
                .map(DepartmentResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public DepartmentResponse findById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("부서를 찾을 수 없습니다."));
        return DepartmentResponse.from(department);
    }

    public DepartmentResponse update(Long id, DepartmentRequest request) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("부서를 찾을 수 없습니다."));

        department.update(request.name());

        return DepartmentResponse.from(department);
    }

    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }
}