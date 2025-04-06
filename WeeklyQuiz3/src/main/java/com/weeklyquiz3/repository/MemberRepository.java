package com.weeklyquiz3.repository;

import com.weeklyquiz3.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}