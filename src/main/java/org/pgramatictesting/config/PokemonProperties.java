package org.pgramatictesting.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tolkv
 * @version 11/09/16
 */
@Data
@ConfigurationProperties("pokemon")
public class PokemonProperties {
  private String home;
}
