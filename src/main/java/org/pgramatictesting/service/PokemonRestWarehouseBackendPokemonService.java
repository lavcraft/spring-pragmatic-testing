package org.pgramatictesting.service;

import lombok.RequiredArgsConstructor;
import org.pgramatictesting.config.PokemonProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author tolkv
 * @version 11/09/16
 */
@Service
@RequiredArgsConstructor
public class PokemonRestWarehouseBackendPokemonService implements PokemonService {
  private final RestOperations restTemplate = new RestTemplate();
  private final PokemonProperties pokemonHome;

  @Override
  public Double getPokemonPower(String pokemonName) {
    ResponseEntity<Double> forEntity = restTemplate.getForEntity(pokemonHome.getHome(), Double.class);
    return forEntity.getBody();
  }
}
