package com.excella.reactor.application.services.impl;

import com.excella.reactor.application.services.AbstractCrudService;
import com.excella.reactor.application.services.SkillCategoryService;
import com.excella.reactor.domain.entities.SkillCategory;
import com.excella.reactor.infrastructure.repositories.SkillCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** A skill category service implementation */
@Service
public class SkillCategoryServiceImpl extends AbstractCrudService<SkillCategory>
    implements SkillCategoryService {

  /**
   * Create instance with SkillCategoryRepository
   *
   * @param repository a respository
   */
  @Autowired
  public SkillCategoryServiceImpl(final SkillCategoryRepository repository) {
    this.repository = repository;
  }
}
