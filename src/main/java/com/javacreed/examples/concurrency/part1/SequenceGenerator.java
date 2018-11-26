package com.javacreed.examples.concurrency.part1;

public class SequenceGenerator {

  private long nextValue;

  public SequenceGenerator(final long nextValue) {
    this.nextValue = nextValue;
  }

  public synchronized long getNextValue() {
    return nextValue++;
  }

}
