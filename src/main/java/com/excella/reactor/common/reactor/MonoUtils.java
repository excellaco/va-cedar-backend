package com.excella.reactor.common.reactor;

import java.util.Optional;
import java.util.concurrent.Callable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/** Utility methods for when working with {@link Mono} API */
public class MonoUtils {
  /**
   * Convenience method for creating a single-element stream from a supplier (so retrieving the
   * element is deferred until subscription). This supplier should NOT be able to return a null
   * value: this will cause a stream error. If null needs to be handled, the Optional variant below
   * can be used. Easiest use case is with a lambda: <code>
   * Mono.fromCallable( () -> barService.getFoo() )</code>
   *
   * <p>In addition, this defaults the stream to use the elastic scheduler, the most general
   * scheduler for multi-threading.
   *
   * @param supplier A callable (usually a lambda) supplying the element
   * @param <T> The type of the element
   * @return The reactive stream (unsubscribed)
   */
  public static <T> Mono<T> fromCallable(final Callable<T> supplier) {
    return Mono.fromCallable(supplier).subscribeOn(Schedulers.elastic());
  }

  /**
   * Convenience method for creating a single-element (or empty) stream from a supplier (so
   * retrieving the element is deferred until subscription). This method adds a step to the stream
   * that "unwraps" the optional - either propagating the element or completing the stream (if
   * empty) Easiest use case is with a lambda: <code>
   * Mono.fromCallableOpt( () -> Optional.ofNullable(barService.getFoo()) )</code>
   *
   * <p>In addition, this defaults the stream to use the elastic scheduler, the most general
   * scheduler for multi-threading.
   *
   * @param supplier A callable (usually a lambda) supplying the element (wrapped in an Optional)
   * @param <T> The type of the element
   * @return The reactive stream (unsubscribed)
   */
  public static <T> Mono<T> fromCallableOpt(final Callable<Optional<T>> supplier) {
    return Mono.fromCallable(supplier).flatMap(Mono::justOrEmpty).subscribeOn(Schedulers.elastic());
  }

  /**
   * Convenience method for creating a multi-element stream from a supplier (so retrieving the
   * elements is deferred until subscription). This supplier should NOT be able to return a null
   * value: this will cause a stream error. It should return an <code>Iterable</code> (most commonly
   * a <code>List</code>) of finite size. Easiest use case is with a lambda: <code>
   * Mono.retrieveAsList( () -> barService.getAllFoos() )</code>
   *
   * <p>In addition, this defaults the stream to use the elastic scheduler, the most general
   * scheduler for multi-threading.
   *
   * @param supplier A callable (usually a lambda) supplying the elements
   * @param <T> The type of the elements
   * @return The reactive stream (unsubscribed)
   */
  public static <T> Flux<T> retrieveAsList(final Callable<Iterable<T>> supplier) {
    return Mono.fromCallable(supplier)
        .flatMapMany(Flux::fromIterable)
        .subscribeOn(Schedulers.elastic());
  }
}
