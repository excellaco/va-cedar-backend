package com.excella.reactor.application.services.impl;

import com.excella.reactor.application.services.AbstractCrudService;
import com.excella.reactor.application.services.EmployeeService;
import com.excella.reactor.domain.entities.Employee;
import com.excella.reactor.infrastructure.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/** Concrete employee service implementation */
@Service
public class EmployeeServiceImpl extends AbstractCrudService<Employee> implements EmployeeService {

  /**
   * Create instance with repository
   *
   * @param repository an EmployeeRepository object
   */
  @Autowired
  public EmployeeServiceImpl(final EmployeeRepository repository) {
    this.repository = repository;
  }

  @Override
  /**
   * Example to show external rest api call to get data from another microservice to populate pojo
   * for api response
   */
  public Mono<Employee> byId(final Long id) {

    return null;
  }
}
