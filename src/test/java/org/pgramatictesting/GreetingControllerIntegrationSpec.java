package org.pgramatictesting;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * X @RunWith(SpringJUnit4ClassRunner.class)
 * X @ContextConfiguration(classes=Application.class, loader=SpringApplicationContextLoader.class)
 * <p>
 * OK @RunWith(SpringJUnit4ClassRunner.class)
 * OK @SpringBootTest
 *
 * @see <a href="https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4">Spring Boot 1.4 testing imporvements</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT) // By default using MOCK webEnvironment
public class GreetingControllerIntegrationSpec {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void should_context_load() throws Exception {
  }

  @Test
  public void checkGreetengController() throws Exception {
    //given
    String pikachu = "Pikachu";

    //when
    String body = this.restTemplate.getForObject("/greet/" + pikachu, String.class);

    //then
    MatcherAssert.assertThat("Should greet valid pokemon", body, Matchers.containsString("Hello " + pikachu));
  }
}