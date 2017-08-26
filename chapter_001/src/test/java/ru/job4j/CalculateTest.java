package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Alexander Eremeev (mailto:eremeev@gmail.com)
* @version $Id$
* @since 24.08.2017
*/

public class CalculateTest {

/**
* Test echo.
*/
@Test
  public void whenTakeNameThenTreeEchoPlusName() {
    String input = "Alexander Eremeev";
    String expect = "Echo, echo, echo : Alexander Eremeev";
    Calculate calc = new Calculate();
    String result = calc.echo(input);
    assertThat(result, is(expect));
  }
}
