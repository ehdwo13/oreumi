package com.weeklyquiz3.service;

import com.weeklyquiz3.dto.TeamDto;
import com.weeklyquiz3.entity.Team;
import com.weeklyquiz3.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public List<TeamDto> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(TeamDto::fromEntity)
                .collect(Collectors.toList());
    }

    public TeamDto addTeam(String name) {
        Team team = new Team();
        team.setName(name);
        return TeamDto.fromEntity(teamRepository.save(team));
    }

    public TeamDto updateTeam(Long id, String newName) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        team.setName(newName);
        return TeamDto.fromEntity(teamRepository.save(team));
    }
}

