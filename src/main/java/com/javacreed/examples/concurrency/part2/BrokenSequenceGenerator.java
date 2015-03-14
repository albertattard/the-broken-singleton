/*
 * #%L
 * The Broken Singleton
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2012 - 2015 Java Creed
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.javacreed.examples.concurrency.part2;

public class BrokenSequenceGenerator {

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

  private static BrokenSequenceGenerator INSTANCE;

  private BrokenSequenceGenerator(final long nextValue) {
    this.nextValue = nextValue;
  }

  public synchronized long getNextValue() {
    return nextValue++;
  }

}
