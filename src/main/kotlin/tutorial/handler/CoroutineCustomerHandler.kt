package tutorial.handler

import kotlinx.coroutines.FlowPreview
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.ok
import tutorial.model.Customer
import tutorial.repo.CoroutineCustomerRepository

@Component
class CoroutineCustomerHandler(
    private val customerRepository: CoroutineCustomerRepository
) {
    suspend fun create(request: ServerRequest): ServerResponse {
        val customer = request.awaitBody<Customer>()
        return ok()
            .json()
            .bodyValueAndAwait(
                customerRepository.save(customer)
            )
    }

    suspend fun get(request: ServerRequest): ServerResponse =
        ok()
            .json()
            .bodyValueAndAwait(customerRepository.findById(request.pathVariable("id").toLong())!!)

    @FlowPreview
    suspend fun getAll(request: ServerRequest): ServerResponse {
        return ok()
            .json()
            .bodyAndAwait(customerRepository.findAll())
    }
}