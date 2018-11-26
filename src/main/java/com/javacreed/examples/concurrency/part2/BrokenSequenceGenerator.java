package com.javacreed.examples.concurrency.part2;

public class BrokenSequenceGenerator {

  private static BrokenSequenceGenerator INSTANCE;

  public static BrokenSequenceGenerator getInstance() {
    if (BrokenSequenceGenerator.INSTANCE == null) {
      synchronized (BrokenSequenceGenerator.class) {
        if (BrokenSequenceGenerator.INSTANCE == null) {
          BrokenSequenceGenerator.INSTANCE = new BrokenSequenceGenerator(1L);
        }
      }
    }
    return BrokenSequenceGenerator.INSTANCE;
  }

  private long nextValue;

  private BrokenSequenceGenerator(final long nextValue) {
    this.nextValue = nextValue;
  }

  public synchronized long getNextValue() {
    return nextValue++;
  }

}
