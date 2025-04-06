package com.weeklyquiz3.dto;

import com.weeklyquiz3.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private Long id;
    private String name;

    public static TeamDto fromEntity(Team team) {
        return new TeamDto(team.getId(), team.getName());
    }
}

