/*
 * Created on Oct 24, 2010
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
package org.fest.assertions.internal.characters;

import static org.fest.assertions.error.ShouldBeLessOrEqual.shouldBeLessOrEqual;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;

import static org.mockito.Mockito.verify;

import org.junit.Test;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.internal.Characters;
import org.fest.assertions.internal.CharactersBaseTest;

/**
 * Tests for <code>{@link Characters#assertNotGreaterThan(AssertionInfo, Character, char)}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class Characters_assertLessThanOrEqualTo_Test extends CharactersBaseTest {

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    characters.assertNotGreaterThan(someInfo(), null, 'a');
  }

  @Test
  public void should_pass_if_actual_is_less_than_other() {
    characters.assertNotGreaterThan(someInfo(), 'a', 'b');
  }

  @Test
  public void should_pass_if_actual_is_equal_to_other() {
    characters.assertNotGreaterThan(someInfo(), 'b', 'b');
  }

  @Test
  public void should_fail_if_actual_is_greater_than_other() {
    AssertionInfo info = someInfo();
    try {
      characters.assertNotGreaterThan(info, 'b', 'a');
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeLessOrEqual('b', 'a'));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_is_null_according_to_custom_comparison_strategy() {
    thrown.expectAssertionError(actualIsNull());
    charactersWithCaseInsensitiveComparisonStrategy.assertNotGreaterThan(someInfo(), null, 'a');
  }

  @Test
  public void should_pass_if_actual_is_less_than_other_according_to_custom_comparison_strategy() {
    charactersWithCaseInsensitiveComparisonStrategy.assertNotGreaterThan(someInfo(), 'a', 'B');
  }

  @Test
  public void should_pass_if_actual_is_equal_to_other_according_to_custom_comparison_strategy() {
    charactersWithCaseInsensitiveComparisonStrategy.assertNotGreaterThan(someInfo(), 'b', 'B');
  }

  @Test
  public void should_fail_if_actual_is_greater_than_other_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();
    try {
      charactersWithCaseInsensitiveComparisonStrategy.assertNotGreaterThan(info, 'B', 'a');
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeLessOrEqual('B', 'a', caseInsensitiveComparisonStrategy));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
