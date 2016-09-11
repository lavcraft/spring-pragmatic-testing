package org.pgramatictesting;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author tolkv
 * @version 11/09/16
 */
public class GreetingControllerTest {

  private GreetingController greetingController;

  @Before
  public void setUp() throws Exception {
    greetingController = new GreetingController();
  }

  @Test
  public void greet() throws Exception {
    //given
    String pikachu = "Pikachu";

    //when
    GreetingController.GreetResult response = greetingController.greet(pikachu);

    //expect
    assertThat("Should greet valid pokemon", response.getSay(), equalTo("Hello " + pikachu));
  }

}