package com.excella.reactor.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.*;

/** Employee object */
@Data
@EqualsAndHashCode(callSuper = true, exclude = "skills")
@ToString(exclude = "skills")
@NoArgsConstructor
@Entity
public class Employee extends DomainModel {
  private static final long serialVersionUID = 1L;

  @Embedded @Valid @NotNull private Bio bio;

  @Embedded @Valid @NotNull private Contact contact;

  @JsonManagedReference
  @OneToMany(
      fetch = FetchType.EAGER,
      mappedBy = "employee",
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  @NotEmpty
  private List<@Valid @NotNull EmployeeSkill> skills;

  private String level;
}
