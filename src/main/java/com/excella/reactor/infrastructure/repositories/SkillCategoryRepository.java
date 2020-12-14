package com.excella.reactor.infrastructure.repositories;

import com.excella.reactor.domain.entities.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/** SkillCategory repository */
public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Long> {}
