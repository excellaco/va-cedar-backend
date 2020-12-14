package com.excella.reactor.infrastructure.api.whizbang;

import com.excella.reactor.domain.entities.Skill;
import com.excella.reactor.domain.entities.SkillScore;
import com.excella.reactor.domain.service.SkillScoreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/** Dummy REST API call to external machine learning service */
public class SkillScoreMLApi implements SkillScoreService {

  private final WebClient client;

  /**
   * Creates a instance with a
   *
   * @param client a WebClient
   */
  @Autowired
  public SkillScoreMLApi(final WebClient client) {
    this.client = client;
  }

  /**
   * Example Rest API call to get score from some fake machine learning service. Keep the domain
   * pure by passing and returning domain entities - not API DTOs. Resiliency patterns should be
   * implemented here to prevent leaky abstractions to the domain. The domain does not care about
   * resiliency.
   *
   * <p>Pattern should be:
   *
   * <ul>
   *   <li>Validate arguments to fail fast
   *   <li>Map domain entity to DTO then call API (not done in the example for expediency sake)
   *   <li>On success, map DTO to domain entity
   *   <li>On failure, potentially retry if recoverable; otherwise, let the error propagate up to
   *       the controller. Only wrap the exception in custom exceptions when you can add information
   *       or facilitate error handling further up the chain
   * </ul>
   *
   * @param skills list of employee skills
   * @return a mono of SkillScore
   */
  @Override
  public Mono<SkillScore> calculate(final List<Skill> skills) {

    if (skills == null || skills.isEmpty()) {
      throw new IllegalArgumentException(String.format("%s is null or empty", "skills"));
    }

    return client
        .post()
        .uri("/calculate")
        .bodyValue(skills)
        .retrieve()
        .bodyToMono(SkillScoreDto.class)
        .map(s -> new SkillScore(s.getScore())); // Use explicit method or class for complex mapping
  }
}
