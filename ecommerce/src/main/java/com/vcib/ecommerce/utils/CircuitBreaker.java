package com.vcib.ecommerce.utils;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class CircuitBreaker {
  
  private enum State {
    CLOSED, OPEN, HALF_OPEN
  }

  private State state = State.CLOSED; 
  private final int failureThreshold;
  private final int halfOpenCalls; 
  private final long openStateTimeout;

  private AtomicInteger failureCount = new AtomicInteger(0); 
  private AtomicInteger successCount = new AtomicInteger(0); 
  private Instant lastFailureTime = Instant.now();

  public CircuitBreaker(int failureThreshold, long openStateTimeout, int halfOpenCalls) {
      this.failureThreshold = failureThreshold;
      this.openStateTimeout = openStateTimeout;
      this.halfOpenCalls = halfOpenCalls;
  }

  public synchronized boolean allowRequest() {
      if (state == State.OPEN && Instant.now().isAfter(lastFailureTime.plusMillis(openStateTimeout))) {
          state = State.HALF_OPEN;
          successCount.set(0);
      }
      return state != State.OPEN; 
  }

  public synchronized void onSuccess() {
      if (state == State.HALF_OPEN) {
          if (successCount.incrementAndGet() >= halfOpenCalls) {
              state = State.CLOSED;
              failureCount.set(0); 
          }
      }
  }

  public synchronized void onFailure() {
      failureCount.incrementAndGet();
      lastFailureTime = Instant.now();
      if (state == State.CLOSED && failureCount.get() >= failureThreshold) {
          state = State.OPEN; 
      } else if (state == State.HALF_OPEN) {
          state = State.OPEN;
      }
  }
}