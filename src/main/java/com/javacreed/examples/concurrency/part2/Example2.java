package com.javacreed.examples.concurrency.part2;

public class Example2 {

  public static void main(final String[] args) {
    final SequenceGenerator a = SequenceGenerator.getInstance();
    System.out.printf("SG A: %d%n", a.getNextValue());
    System.out.printf("SG A: %d%n", a.getNextValue());

    final SequenceGenerator b = SequenceGenerator.getInstance();
    System.out.printf("SG B: %d%n", b.getNextValue());
  }
}
