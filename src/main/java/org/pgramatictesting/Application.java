package org.pgramatictesting;

import lombok.extern.slf4j.Slf4j;
import org.pgramatictesting.config.PokemonProperties;
import org.pgramatictesting.persistence.CustomerRepository;
import org.pgramatictesting.persistence.domain.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import static java.lang.String.*;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(PokemonProperties.class)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
    return (args) -> {
      customerRepository.save(new Customer("Beer fabric", "Good beer provider"));
      customerRepository.save(new Customer("Vodka fabric", "Ugly alcohol provider"));

      customerRepository.findAll()
              .forEach(customer -> log.info("{}",
                      format("%10s : %-20s - %-30s",
                              customer.getId(),
                              customer.getName(),
                              customer.getRole())
                      )
              );
    };
  }
}
