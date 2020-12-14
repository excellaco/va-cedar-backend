package com.excella.reactor.infrastructure.repositories;

import com.excella.reactor.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Employee repository for DB calls */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
