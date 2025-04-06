package com.weeklyquiz3.service;

import com.weeklyquiz3.dto.MemberDto;
import com.weeklyquiz3.entity.Member;
import com.weeklyquiz3.entity.Team;
import com.weeklyquiz3.repository.MemberRepository;
import com.weeklyquiz3.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    public List<MemberDto> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(MemberDto::fromEntity)
                .collect(Collectors.toList());
    }

    public MemberDto addMemberToTeam(Long teamId, String memberName) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        Member member = new Member();
        member.setName(memberName);
        member.setTeam(team);

        Member saved = memberRepository.save(member);
        return MemberDto.fromEntity(saved);
    }
}
