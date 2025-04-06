package com.weeklyquiz3.repository;

import com.weeklyquiz3.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}