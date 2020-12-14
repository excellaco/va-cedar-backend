package com.excella.reactor.infrastructure.repositories;

import com.excella.reactor.domain.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** A Skill Repository */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {}
