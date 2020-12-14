package com.excella.reactor.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** The category for a skill */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "skill_category")
public class SkillCategory extends DomainModel {

  private static final long serialVersionUID = 1L;

  @NotEmpty private String name;
}
