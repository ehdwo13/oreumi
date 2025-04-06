package com.weeklyquiz3.dto;

import com.weeklyquiz3.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String name;
    private TeamDto team;

    public static MemberDto fromEntity(Member member) {
        return new MemberDto(
                member.getId(),
                member.getName(),
                TeamDto.fromEntity(member.getTeam())
        );
    }
}
