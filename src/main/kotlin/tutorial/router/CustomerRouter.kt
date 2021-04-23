package tutorial.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import tutorial.handler.CustomerHandler

@Configuration
open class CustomerRouter {
    @Bean("router")
    open fun route(handler: CustomerHandler): RouterFunction<ServerResponse> =
        router {
            accept(MediaType.APPLICATION_JSON)
                .nest {
                    POST("customer", handler::create)
                    GET("customer", handler::getAll)
                    GET("customer/{id}", handler::get)
                }
        }
}