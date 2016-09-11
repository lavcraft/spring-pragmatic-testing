package org.pgramatictesting;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Tests based on standaloneSetup with minimal SpringDispatcherServlet context
 * @see org.springframework.test.web.servlet.setup.MockMvcBuilders#standaloneSetup(Object...)
 *
 * Use it for testing representation logic for controller separatly
 */
public class GreetingControllerImproveTest {

  // X private GreetingController greetingController;
  private MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
  // X greetingController = new GreetingController();
    mockMvc = standaloneSetup(new GreetingController())
        .build();
  }

  @Test //Should greet valid pokemon
  public void greet() throws Exception {
    //given
    String pikachu = "Pikachu";

    //expect
    mockMvc.perform(
        get("/greet/" + pikachu)
            .accept(MediaType.APPLICATION_JSON)
    )
        .andExpect(status().isOk())
        .andExpect(
            jsonPath("$.say", equalTo("Hello " + pikachu)) // check it!
        )
        .andExpect(content().string(containsString(pikachu))); // check it again, another way

    /* behind the scene:

    Use apply  for extenxt mock mvc functionality
      mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(springSecurity()) 1
        .build();

     and now:
       mvc.perform(post("/").with(csrf()))
       mvc.perform(post("/").with(csrf().useInvalidToken()))
       ...
     */

  }

}