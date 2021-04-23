package tutorial.controller

import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*
import tutorial.model.Customer
import tutorial.repo.CoroutineCustomerRepository

@RestController
class CustomerController(
    private val coroutineCustomerRepository: CoroutineCustomerRepository
) {
    @PostMapping("/ccCustomer")
    suspend fun create(@RequestBody customer: Customer): Customer = coroutineCustomerRepository.save(customer)

    @GetMapping("/ccCustomer/{id}")
    suspend fun get(@PathVariable id: String): Customer = coroutineCustomerRepository.findById(id.toLong())!!

    @GetMapping("/ccCustomer")
    fun getAll(): Flow<Customer> = coroutineCustomerRepository.findAll()
}