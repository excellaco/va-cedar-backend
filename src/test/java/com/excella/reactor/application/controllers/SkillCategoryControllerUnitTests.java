package com.excella.reactor.application.controllers;

import static org.mockito.Mockito.mock;

import com.excella.reactor.application.services.SkillCategoryService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SuppressWarnings("PMD.UnusedPrivateMethod")
public class SkillCategoryControllerUnitTests {

  private SkillCategoryService skillCategoryService;
  private SkillCategoryController skillCategoryController;

  @BeforeMethod
  private void beforeEach() {
    skillCategoryService = mock(SkillCategoryService.class);
    skillCategoryController = new SkillCategoryController(skillCategoryService);
  }

  @Test
  public void testGetService() {
    Assert.assertEquals(skillCategoryService, skillCategoryController.getService());
  }
}
