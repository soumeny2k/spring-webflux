package tutorial.router

import kotlinx.coroutines.FlowPreview
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter
import tutorial.handler.CoroutineCustomerHandler

@Configuration
class CoroutineCustomerRouter {
    @Bean("cRouter")
    @FlowPreview
    fun route(handler: CoroutineCustomerHandler): RouterFunction<ServerResponse> =
        coRouter {
            accept(MediaType.APPLICATION_JSON)
                .nest {
                    POST("cCustomer", handler::create)
                    GET("cCustomer", handler::getAll)
                    GET("cCustomer/{id}", handler::get)
                }
        }
}