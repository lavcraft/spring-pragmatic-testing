package org.pgramatictesting.persistence;

import org.pgramatictesting.persistence.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tolkv
 * @since 9/18/2016
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
