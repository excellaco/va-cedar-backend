package com.excella.reactor.infrastructure.api.whizbang;

import com.excella.reactor.domain.entities.Skill;
import com.excella.reactor.domain.entities.SkillCategory;
import com.excella.reactor.domain.entities.SkillScore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * Example integration test using real mocks rather than in memory mocks like mockito. We are
 * trading some speed but gaining readability, maintainability, and robustness by being able to test
 * the entire integration stack. The tradeoff in speed is worth it here.
 */
@SpringBootTest
public class SkillScoreMLApiTest {

  private static MockWebServer mockBackEnd;
  private static ObjectMapper objectMapper;

  private SkillScoreMLApi skillScoreMLApi;

  @BeforeClass
  private static void setUp() throws IOException {
    mockBackEnd = new MockWebServer();
    mockBackEnd.start();

    objectMapper = new ObjectMapper();
  }

  @AfterClass
  private static void tearDown() throws IOException {
    mockBackEnd.shutdown();
  }

  @BeforeMethod
  private void runBeforeEachMethod() {
    String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());

    final var config = new SkillScoreMLApiConfig(baseUrl);
    skillScoreMLApi = new SkillScoreMLApi(config.webClient());
  }

  @Test
  private void givenValidListOfSkills_whenCalculate_thenReturnMonoOfSkillScore()
      throws JsonProcessingException {

    // Arrange
    final var skills = new ArrayList<Skill>();
    skills.add(createSkill("Java", "Development"));
    final var score = 78;
    final var skillScoreDtoResponse = new SkillScoreDto(score);
    final var expectedSkillScore = new SkillScore(score);

    mockBackEnd.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .setBody(objectMapper.writeValueAsString(skillScoreDtoResponse)));

    // Act
    Mono<SkillScore> skillScoreMono = skillScoreMLApi.calculate(skills);

    // Assert
    StepVerifier.create(skillScoreMono).expectNext(expectedSkillScore).verifyComplete();
  }

  private Skill createSkill(String name, String skillCategoryName) {
    final var category = new SkillCategory();
    category.setName(skillCategoryName);

    final var skill = new Skill();
    skill.setName(name);
    skill.setCategory(category);

    return skill;
  }
}
