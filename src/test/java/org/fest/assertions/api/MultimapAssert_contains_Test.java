package org.fest.assertions.api;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.entry;
import static org.fest.assertions.api.GUAVA.assertThat;
import static org.fest.util.FailureMessages.actualIsNull;

import static org.junit.Assert.fail;

import org.junit.Test;

import org.fest.assertions.data.MapEntry;

public class MultimapAssert_contains_Test extends MultimapAssertBaseTest {

  @Test
  public void should_pass_if_actual_contains_given_entries() {
    assertThat(actual).contains(entry("Bulls", "Derrick Rose"));
    assertThat(actual).contains(entry("Lakers", "Kobe Bryant"), entry("Spurs", "Tim Duncan"));
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    actual = null;
    assertThat(actual).contains(entry("Lakers", "Kobe Bryant"));
  }

  @Test
  public void should_fail_if_entries_to_look_for_are_null() {
    thrown.expect(IllegalArgumentException.class, "The entries to look for should not be null");
    assertThat(actual).contains((MapEntry[]) null);
  }

  @Test
  public void should_fail_if_entries_to_look_for_are_empty() {
    thrown.expect(IllegalArgumentException.class, "The entries to look for should not be empty");
    assertThat(actual).contains();
  }

  @Test
  public void should_fail_if_actual_does_not_contain_all_given_entries() {
    try {
      assertThat(actual).contains(entry("Lakers", "Kobe Bryant"), entry("Spurs", "Derrick Rose"));
    } catch (AssertionError e) {
      assertThat(e)
          .hasMessage(
              "expecting:\n"
                  + "<{Lakers=[Kobe Bryant, Magic Johnson, Kareem Abdul Jabbar], Bulls=[Michael Jordan, Scottie Pippen, Derrick Rose], Spurs=[Tony Parker, Tim Duncan, Manu Ginobili]}>\n"
                  + " to contain:\n"
                  + "<[MapEntry[key='Lakers', value='Kobe Bryant'], MapEntry[key='Spurs', value='Derrick Rose']]>\n"
                  + " but could not find:\n<[MapEntry[key='Spurs', value='Derrick Rose']]>\n");
      return;
    }
    fail("Assertion error expected");
  }

}