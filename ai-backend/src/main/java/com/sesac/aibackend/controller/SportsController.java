package com.sesac.aibackend.controller;

import com.sesac.aibackend.domain.Sports;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sports")
public class SportsController {

    private final List<Sports> sportsList = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping
    public List<Sports> findAll() {
        return sportsList;
    }

    @GetMapping("/{id}")
    public Sports findById(@PathVariable Long id) {
        return sportsList.stream()
                .filter(sports -> sports.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 스포츠를 찾을 수 없습니다. id=" + id));
    }

    @PostMapping
    public Sports create(@RequestBody Sports sports) {
        sports.setId(nextId++);
        sportsList.add(sports);
        return sports;
    }

    @PutMapping("/{id}")
    public Sports update(@PathVariable Long id, @RequestBody Sports request) {
        Sports sports = findById(id);

        sports.setName(request.getName());
        sports.setCategory(request.getCategory());
        sports.setPlayerCount(request.getPlayerCount());
        sports.setIndoor(request.isIndoor());

        return sports;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Sports sports = findById(id);
        sportsList.remove(sports);
        return "삭제 완료";
    }
}