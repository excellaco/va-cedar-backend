package com.excella.reactor.application.controllers;

import com.excella.reactor.application.services.CrudService;
import com.excella.reactor.application.services.SkillService;
import com.excella.reactor.domain.entities.Skill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** A Skill controller */
@RestController
@RequestMapping("/skill")
@Slf4j
public class SkillController extends CrudController<Skill> {
  private final SkillService service;

  /**
   * Create instance with SkillService object
   *
   * @param service a SkillService object
   */
  @Autowired
  public SkillController(final SkillService service) {
    this.service = service;
  }

  @Override
  CrudService<Skill> getService() {
    return this.service;
  }
}
