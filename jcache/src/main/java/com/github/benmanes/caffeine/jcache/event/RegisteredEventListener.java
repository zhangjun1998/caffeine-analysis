/*
 * Copyright 2015 Ben Manes. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.benmanes.caffeine.jcache.event;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.event.CacheEntryEventFilter;
import javax.cache.event.CacheEntryListener;

/**
 * The registration of a {@link CacheEntryListener} for event dispatching.
 *
 * @author ben.manes@gmail.com (Ben Manes)
 */
final class Registration<K, V> {
  private final CacheEntryListenerConfiguration<K, V> configuration;
  private final EventTypeAwareListener<K, V> listener;
  private final CacheEntryEventFilter<K, V> filter;

  public Registration(CacheEntryListenerConfiguration<K, V> configuration,
      CacheEntryEventFilter<K, V> filter, EventTypeAwareListener<K, V> listener) {
    this.configuration = requireNonNull(configuration);
    this.listener = requireNonNull(listener);
    this.filter = requireNonNull(filter);
  }

  /** @return the configuration */
  public CacheEntryListenerConfiguration<K, V> getConfiguration() {
    return configuration;
  }

  /** @return the registered listener */
  public EventTypeAwareListener<K, V> getCacheEntryListener() {
    return listener;
  }

  /** @return the registered filter */
  public CacheEntryEventFilter<K, V> getCacheEntryFilter() {
    return filter;
  }

  /** See {@link CacheEntryListenerConfiguration#isOldValueRequired()}. */
  public boolean isOldValueRequired() {
    return configuration.isOldValueRequired();
  }

  /** See {@link CacheEntryListenerConfiguration#isSynchronous()}. */
  public boolean isSynchronous() {
    return configuration.isSynchronous();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof Registration<?, ?>)) {
      return false;
    }
    Registration<?, ?> other = (Registration<?, ?>) o;
    return Objects.equals(listener, other.listener)
        && Objects.equals(filter, other.filter)
        && Objects.equals(configuration, other.configuration);
  }

  @Override
  public int hashCode() {
    return listener.hashCode();
  }

  @Override
  public String toString() {
    return configuration.toString();
  }
}
