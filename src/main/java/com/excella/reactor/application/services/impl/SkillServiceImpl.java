package com.excella.reactor.application.services.impl;

import com.excella.reactor.application.services.AbstractCrudService;
import com.excella.reactor.application.services.SkillService;
import com.excella.reactor.domain.entities.Skill;
import com.excella.reactor.infrastructure.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Skill service implementation */
@Service
public class SkillServiceImpl extends AbstractCrudService<Skill> implements SkillService {

  /**
   * Create an instance with a SkillRepository object
   *
   * @param repository a SkillRepository object
   */
  @Autowired
  public SkillServiceImpl(final SkillRepository repository) {
    this.repository = repository;
  }
}
