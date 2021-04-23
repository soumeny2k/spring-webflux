package tutorial.handler

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromPublisher
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.json
import reactor.core.publisher.Mono
import tutorial.model.Customer
import tutorial.repo.CustomerRepository

@Component
class CustomerHandler(
    private val customerRepository: CustomerRepository
) {
    fun create(request: ServerRequest): Mono<ServerResponse> {
        val customer = request.bodyToMono(Customer::class.java)
        return ok()
            .json()
            .body(
                fromPublisher(
                    customer.flatMap {
                        customerRepository.save(it)
                    }, Customer::class.java
                )
            )
    }

    fun get(request: ServerRequest): Mono<ServerResponse> =
        ok()
            .json()
            .body(customerRepository.findById(request.pathVariable("id").toLong()))

    fun getAll(request: ServerRequest): Mono<ServerResponse>  {
        return ok()
            .json()
            .body(fromPublisher(customerRepository.findAll(), Customer::class.java))
    }
}