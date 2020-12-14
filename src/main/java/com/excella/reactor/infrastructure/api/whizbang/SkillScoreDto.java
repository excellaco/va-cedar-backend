package com.excella.reactor.infrastructure.api.whizbang;

import lombok.*;

/**
 * Skill Score DTO object for whizbang api call
 *
 * <p>NOTE: had to use all these annotations for jackson serialization/deserialization. Would have
 * preferred using @Value but that does not work with jackson.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.NONE)
class SkillScoreDto {
  private int score;
}
