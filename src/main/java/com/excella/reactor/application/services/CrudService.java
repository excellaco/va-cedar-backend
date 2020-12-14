package com.excella.reactor.application.services;

import com.excella.reactor.domain.entities.DomainModel;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Base interface for a CRUD service
 *
 * @param <T> A type that extends DomainModel
 */
public interface CrudService<T extends DomainModel> {

  /**
   * gets all objects of type T from the database
   *
   * @param pageable pagination information
   * @return all database objects of type T
   */
  Flux<T> all(Pageable pageable);

  /**
   * Gets a an object of type T from the database by the object's id.
   *
   * @param id id of the crud object
   * @return Mono object of type T
   */
  Mono<T> byId(Long id);

  /**
   * saves an object in the database
   *
   * @param t an object of type t
   * @return a Mono of the same object
   */
  Mono<T> save(T t);

  /**
   * looks up database object by id and updates fields saves object if one with the id does not
   * already exist
   *
   * @param id id of the database object to update
   * @param t object with the new values
   * @return a Mono of the updated object
   */
  Mono<T> update(Long id, T t);

  /**
   * deletes an object of type T from the database by the object's id
   *
   * @param id id of the database object to delete
   * @return a Mono of the deleted object
   */
  Mono<T> delete(Long id);
}
