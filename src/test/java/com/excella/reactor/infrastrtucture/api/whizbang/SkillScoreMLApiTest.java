package com.excella.reactor.infrastrtucture.api.whizbang;

import com.excella.reactor.domain.entities.Skill;
import com.excella.reactor.infrastructure.api.whizbang.SkillScoreMLApi;
import java.util.ArrayList;
import org.mockito.Mockito;
import org.springframework.web.reactive.function.client.WebClient;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Only testing argument validations here. For more complicated scenarios calling the API, favor
 * integration tests. See inttest folder for an example.
 */
public class SkillScoreMLApiTest {
  private SkillScoreMLApi api;

  @BeforeClass
  private void beforeEach() {
    this.api = new SkillScoreMLApi(Mockito.mock(WebClient.class));
  }

  @Test(expectedExceptions = {IllegalArgumentException.class})
  private void givenNullSkillList_whenCalculate_thenThrowException() {
    this.api.calculate(null);
  }

  @Test(expectedExceptions = {IllegalArgumentException.class})
  private void givenEmptySkillList_whenCalculate_thenThrowException() {
    this.api.calculate(new ArrayList<Skill>());
  }
}
