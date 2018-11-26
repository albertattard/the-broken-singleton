package com.javacreed.examples.concurrency.part3;

public class SequenceGenerator {

  private static class SingletonHolder {
    private static final SequenceGenerator INSTANCE = new SequenceGenerator(1L);
  }

  public static SequenceGenerator getInstance() {
    return SingletonHolder.INSTANCE;
  }

  private long nextValue;

  private SequenceGenerator() {}

  private SequenceGenerator(final long nextValue) {
    this.nextValue = nextValue;
  }

  public synchronized long getNextValue() {
    return nextValue++;
  }

}
