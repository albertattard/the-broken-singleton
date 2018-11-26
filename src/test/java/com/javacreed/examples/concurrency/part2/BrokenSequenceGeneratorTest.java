package com.javacreed.examples.concurrency.part2;

import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.AssertionFailedError;

public class BrokenSequenceGeneratorTest {

  @Test
  public void test() throws Throwable {

    final int size = 12;

    final CyclicBarrier cyclicBarrier = new CyclicBarrier(size);

    final AtomicReference<Throwable> exception = new AtomicReference<>();

    final Set<Long> generatedValues = new HashSet<>(size);

    final Set<BrokenSequenceGenerator> instances = Collections
                                                              .newSetFromMap(new IdentityHashMap<BrokenSequenceGenerator, Boolean>());

    final List<Thread> threads = new LinkedList<>();
    for (int i = 0; i < size; i++) {
      final Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            cyclicBarrier.await();
          } catch (InterruptedException | BrokenBarrierException e) {
            exception.compareAndSet(null, e);
            return;
          }

          final BrokenSequenceGenerator sequenceGenerator = BrokenSequenceGenerator.getInstance();
          final long value = sequenceGenerator.getNextValue();

          // Synchronise the access as the collections used are not thread-safe
          synchronized (BrokenSequenceGeneratorTest.class) {
            if (!generatedValues.add(value)) {
              exception.compareAndSet(null, new AssertionFailedError("Duplicate value " + value));
              return;
            }

            instances.add(sequenceGenerator);
          }
        }
      });
      thread.start();
      threads.add(thread);
    }

    for (final Thread thread : threads) {
      thread.join();
    }

    if (exception.get() != null) {
      throw exception.get();
    }

    switch (instances.size()) {
    case 0:
      Assert.fail("Expected one instance, but found none");
      break;
    case 1:
      break;
    default:
      Assert.fail("Expected one instance, but found many");
    }

  }

}
