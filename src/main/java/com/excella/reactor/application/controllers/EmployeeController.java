package com.excella.reactor.application.controllers;

import com.excella.reactor.application.services.CrudService;
import com.excella.reactor.application.services.EmployeeService;
import com.excella.reactor.domain.entities.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** A controller for Employees */
@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController extends CrudController<Employee> {

  private final EmployeeService service;

  /**
   * Creates an instance with EmployeeService
   *
   * @param service an EmployeeService instance
   */
  @Autowired
  public EmployeeController(final EmployeeService service) {
    this.service = service;
  }

  @Override
  CrudService<Employee> getService() {
    return this.service;
  }
}
