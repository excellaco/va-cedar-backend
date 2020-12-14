package com.excella.reactor.application.controllers;

import static org.mockito.Mockito.mock;

import com.excella.reactor.application.services.SkillService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SuppressWarnings("PMD.UnusedPrivateMethod")
public class SkillControllerUnitTests {

  private SkillService skillService;
  private SkillController skillController;

  @BeforeMethod
  private void beforeEach() {
    skillService = mock(SkillService.class);
    skillController = new SkillController(skillService);
  }

  @Test
  public void testGetService() {
    Assert.assertEquals(skillService, skillController.getService());
  }
}
