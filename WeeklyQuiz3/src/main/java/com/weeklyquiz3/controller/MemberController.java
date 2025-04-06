package com.weeklyquiz3.controller;

import com.weeklyquiz3.dto.MemberDto;
import com.weeklyquiz3.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<MemberDto> getMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping("/teams/{teamId}")
    public MemberDto addMember(
            @PathVariable Long teamId,
            @RequestBody Map<String, String> request) {

        String name = request.get("name");
        return memberService.addMemberToTeam(teamId, name);
    }
}
