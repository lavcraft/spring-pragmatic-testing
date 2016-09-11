package org.pgramatictesting;

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

}
