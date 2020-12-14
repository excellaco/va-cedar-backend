package com.excella.reactor.application.controllers;

import com.excella.reactor.application.services.CrudService;
import com.excella.reactor.application.services.SkillCategoryService;
import com.excella.reactor.domain.entities.SkillCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** SkillCategory controller */
@RestController
@RequestMapping("/skill-category")
@Slf4j
public class SkillCategoryController extends CrudController<SkillCategory> {

  private final SkillCategoryService service;

  /**
   * Create instance with SkillCategoryService object
   *
   * @param service a SkillCategoryService object
   */
  @Autowired
  public SkillCategoryController(final SkillCategoryService service) {
    this.service = service;
  }

  @Override
  CrudService<SkillCategory> getService() {
    return this.service;
  }
}
