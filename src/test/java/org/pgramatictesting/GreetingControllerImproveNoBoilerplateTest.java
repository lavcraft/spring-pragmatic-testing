package org.pgramatictesting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests based on standaloneSetup with minimal SpringDispatcherServlet context
 *
 * @see org.springframework.test.web.servlet.setup.MockMvcBuilders#standaloneSetup(Object...)
 * <p>
 * Use it for testing representation logic for controller separatly
 * This is a good way, because you dont need to configure mockMvc manuall - spring boot bootstrap context automatically and valid!
 * Autoconfiguration by @WebMvcTest provide additional ability for testing (like rich logging, disable caching and other..)
 * <p>
 * If you are looking to load your full application configuration and use MockMVC, you
 * should consider {@link org.springframework.boot.test.context.SpringBootTest @SpringBootTest} combined with
 * {@link org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc @AutoConfigureMockMvc} rather than this annotation.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
@AutoConfigureRestDocs("build/generated-snippets")
public class GreetingControllerImproveNoBoilerplateTest {

  // X private GreetingController greetingController;
  @Autowired
  private MockMvc mockMvc;

// X  @Before
// X public void setUp() throws Exception {
// X    greetingController = new GreetingController();
// X    mockMvc = standaloneSetup(new GreetingController())
// X       .build();
// X }

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
        .andExpect(content().string(containsString(pikachu))) // check it again, another way
        .andDo(document("greet pokemon",
            preprocessRequest(prettyPrint()),
            requestHeaders(
                headerWithName("Accept")
                    .description("Content type :)")
            ),
            responseFields(
                fieldWithPath(".name")
                    .description("Pokemon name")
                    .type(JsonFieldType.STRING),
                fieldWithPath(".say")
                    .description("Pokemon greetings")
                    .type(JsonFieldType.STRING)
            )
        )); // <-- need for generate docs and add descriptions for entities in doc

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