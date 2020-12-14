package com.excella.reactor.application.services;

import com.excella.reactor.common.exceptions.ResourceNotFoundException;
import com.excella.reactor.common.reactor.MonoUtils;
import com.excella.reactor.domain.entities.DomainModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Abstract class for all CRUD services, implementing the CrudService interface. Provides
 * convenience implementations that translate JPA repository methods into a reactive context
 *
 * @param <T> A type that extends DomainModel
 */
public abstract class AbstractCrudService<T extends DomainModel> implements CrudService<T> {

  protected JpaRepository<T, Long> repository;

  @Override
  public Flux<T> all(final Pageable pageable) {
    return MonoUtils.retrieveAsList(() -> repository.findAll(pageable));
  }

  @Override
  public Mono<T> byId(final Long id) {
    return MonoUtils.fromCallableOpt(() -> repository.findById(id))
        .switchIfEmpty(
            Mono.error(ResourceNotFoundException.of("Resource with id " + id + " not found")));
  }

  @Override
  public Mono<T> save(final T t) {
    return MonoUtils.fromCallable(() -> repository.save(t));
  }

  @Override
  public Mono<T> update(final Long id, final T t) {
    return byId(id)
        .map(
            p -> {
              t.setId(id);
              return t;
            })
        .flatMap(this::save);
  }

  @Override
  public Mono<T> delete(final Long id) {
    return byId(id)
        .flatMap(
            p ->
                MonoUtils.fromCallable(
                    () -> {
                      repository.delete(p);
                      return p;
                    }));
  }
}
