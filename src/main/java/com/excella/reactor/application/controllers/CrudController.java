package com.excella.reactor.application.controllers;

import com.excella.reactor.application.services.CrudService;
import com.excella.reactor.domain.entities.DomainModel;
import com.excella.reactor.validation.groups.PostChecks;
import javax.validation.groups.Default;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A CRUD controller
 *
 * @param <T> a type that extends DomainModel
 */
@Slf4j
public abstract class CrudController<T extends DomainModel> {
  protected static final int DEFAULT_PAGE_SIZE = 10;

  private static final String JSON = MimeTypeUtils.APPLICATION_JSON_VALUE;

  /**
   * gets all database objects of type T
   *
   * @param page page number to retrieve
   * @param size size of each page
   * @return Publisher of all resources of type T
   */
  @GetMapping(value = "", name = "Get all of resource", produces = JSON)
  Publisher<T> getAll(
      final @RequestParam(value = "page", required = false) Integer page,
      final @RequestParam(value = "size", required = false) Integer size) {
    final PageRequest pageable =
        PageRequest.of(page == null ? 0 : page, size == null ? DEFAULT_PAGE_SIZE : size);
    return getService().all(pageable).doOnSubscribe(result -> log.info("Getting all"));
  }

  /**
   * gets a resource by its id
   *
   * @param id id of the resource
   * @return a Publisher of the resource
   */
  @GetMapping(value = "{id}", name = "Get resource item by id", produces = JSON)
  Publisher<T> getById(final @PathVariable Long id) {
    return getService().byId(id).doOnSubscribe(result -> log.info("Getting id {}", id));
  }

  /**
   * creates a new object in the database
   *
   * @param t object to save
   * @return Publisher providing the newly created object
   */
  @PostMapping(value = "", name = "Add a new resource item", produces = JSON)
  Publisher<T> create(final @RequestBody @Validated({PostChecks.class, Default.class}) T t) {
    return getService()
        .save(t)
        .doOnSubscribe(result -> log.info("Adding new item {}", t.toString()));
  }

  /**
   * update an object by id to new values
   *
   * @param id id of object to update
   * @param t object containing new values
   * @return Publisher of the object with updated values
   */
  @PutMapping(value = "{id}", name = "Update a resource by id", produces = JSON)
  Publisher<T> update(final @PathVariable Long id, final @RequestBody @Validated T t) {
    return getService().update(id, t).doOnSubscribe(result -> log.info("Updating item {}", id));
  }

  /**
   * removes an object from the database by id
   *
   * @param id id of the object to delete
   * @return Publisher of the object that was removed
   */
  @DeleteMapping(value = "{id}", name = "Delete resource by id", produces = JSON)
  Publisher<T> removeById(final @PathVariable Long id) {
    return getService().delete(id).doOnSubscribe(result -> log.info("Deleting id {}", id));
  }

  /**
   * get the CRUD service associated with this controller
   *
   * @return the service
   */
  abstract CrudService<T> getService();
}
