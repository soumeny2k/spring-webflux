package tutorial.repo

import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import tutorial.model.Customer

interface CoroutineCustomerRepository : CoroutineCrudRepository<Customer, Long> {
    @Query("SELECT * FROM customer")
    suspend fun getAll(): Flow<Customer>
}