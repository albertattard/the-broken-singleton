package com.javacreed.examples.concurrency.part2;

public class SequenceGenerator {

  private static SequenceGenerator INSTANCE;

  public static synchronized SequenceGenerator getInstance() {
    if (SequenceGenerator.INSTANCE == null) {
      SequenceGenerator.INSTANCE = new SequenceGenerator(1L);
    }
    return SequenceGenerator.INSTANCE;
  }

  private long nextValue;

  private SequenceGenerator(final long nextValue) {
    this.nextValue = nextValue;
  }

  public synchronized long getNextValue() {
    return nextValue++;
  }

}
