/*
 * Created on Oct 20, 2010
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.internal.longs;

import static org.fest.assertions.error.ShouldBeGreater.shouldBeGreater;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;

import static org.mockito.Mockito.verify;

import org.junit.Test;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.internal.Longs;
import org.fest.assertions.internal.LongsBaseTest;

/**
 * Tests for <code>{@link Longs#assertGreaterThan(AssertionInfo, Long, long)}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class Longs_assertGreaterThan_Test extends LongsBaseTest {

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    longs.assertGreaterThan(someInfo(), null, 8L);
  }

  @Test
  public void should_pass_if_actual_is_greater_than_other() {
    longs.assertGreaterThan(someInfo(), 8L, 6L);
  }

  @Test
  public void should_fail_if_actual_is_equal_to_other() {
    AssertionInfo info = someInfo();
    try {
      longs.assertGreaterThan(info, 6L, 6L);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeGreater(6L, 6L));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_is_less_than_other() {
    AssertionInfo info = someInfo();
    try {
      longs.assertGreaterThan(info, 6L, 8L);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeGreater(6L, 8L));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  // ------------------------------------------------------------------------------------------------------------------
  // tests using a custom comparison strategy
  // ------------------------------------------------------------------------------------------------------------------

  @Test
  public void should_pass_if_actual_is_greater_than_other_according_to_custom_comparison_strategy() {
    longsWithAbsValueComparisonStrategy.assertGreaterThan(someInfo(), 8L, 6L);
  }

  @Test
  public void should_fail_if_actual_is_equal_to_other_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();
    try {
      longsWithAbsValueComparisonStrategy.assertGreaterThan(info, -6L, 6L);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeGreater(-6L, 6L, absValueComparisonStrategy));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_is_less_than_other_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();
    try {
      longsWithAbsValueComparisonStrategy.assertGreaterThan(info, 6L, -8L);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeGreater(6L, -8L, absValueComparisonStrategy));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

}
