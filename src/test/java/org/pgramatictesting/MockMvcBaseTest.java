package org.pgramatictesting;

import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author tolkv
 * @version 11/09/16
 */
@AutoConfigureMockMvc
public class MockMvcBaseTest {

  @Autowired
  public MockMvc mockMvc;

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(
      WireMockConfiguration.wireMockConfig()
          .port(12000)
          .notifier(new ConsoleNotifier(true))
  );
}
