package com.weeklyquiz3.controller;

import com.weeklyquiz3.dto.TeamDto;
import com.weeklyquiz3.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public List<TeamDto> getTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping
    public TeamDto addTeam(@RequestBody Map<String, String> request) {
        return teamService.addTeam(request.get("name"));
    }

    @PutMapping("/{id}")
    public TeamDto updateTeam(@PathVariable Long id,
                              @RequestBody Map<String, String> request) {
        return teamService.updateTeam(id, request.get("name"));
    }
}
