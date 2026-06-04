package com.sesac.aibackend.controller;

import com.sesac.aibackend.domain.Sports;
import com.sesac.aibackend.dto.SportsRequest;
import com.sesac.aibackend.dto.SportsResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sports")
public class SportsController {

    private final List<Sports> sportsList = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping
    public List<SportsResponse> findAll() {
        return sportsList.stream()
                .map(SportsResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public SportsResponse findById(@PathVariable Long id) {
        Sports sports = findSportsById(id);
        return SportsResponse.from(sports);
    }

    @PostMapping
    public SportsResponse create(@Valid @RequestBody SportsRequest request) {
        Sports sports = request.toEntity();
        sports.setId(nextId++);

        sportsList.add(sports);

        return SportsResponse.from(sports);
    }

    @PutMapping("/{id}")
    public SportsResponse update(
            @PathVariable Long id,
            @Valid @RequestBody SportsRequest request
    ) {
        Sports sports = findSportsById(id);

        sports.setName(request.name());
        sports.setCategory(request.category());
        sports.setPlayerCount(request.playerCount());
        sports.setIndoor(request.indoor());

        return SportsResponse.from(sports);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Sports sports = findSportsById(id);
        sportsList.remove(sports);

        return "삭제 완료";
    }

    private Sports findSportsById(Long id) {
        return sportsList.stream()
                .filter(sports -> sports.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 스포츠를 찾을 수 없습니다. id=" + id));
    }
}