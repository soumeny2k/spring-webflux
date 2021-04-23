package tutorial.repo

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import tutorial.model.Customer

interface CustomerRepository: ReactiveCrudRepository<Customer, Long>