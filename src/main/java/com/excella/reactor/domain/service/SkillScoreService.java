package com.excella.reactor.domain.service;

import com.excella.reactor.domain.entities.Skill;
import com.excella.reactor.domain.entities.SkillScore;
import java.util.List;
import reactor.core.publisher.Mono;

/** Interface to calculate a skill score based on their skills */
public interface SkillScoreService {

  /**
   * A skill score is returned from the service
   *
   * @param skills list of Skills
   * @return a mono of SkillScore
   */
  Mono<SkillScore> calculate(List<Skill> skills);
}
