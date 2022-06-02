package com.splanes.grocery.ms_customer.application

import com.splanes.grocery.ms_customer.application.security.JWTService
import com.splanes.grocery.ms_customer.application.security.authentication.CustomerAuthDetailsService
import com.splanes.grocery.ms_customer.application.security.authorization.JWTAuthorizationFilter
import com.splanes.grocery.ms_customer.domain.model.customer.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.codec.json.AbstractJackson2Decoder
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.function.server.router
import java.net.URI

@Configuration
@EnableWebFlux
@EnableWebFluxSecurity
open class WebConfig : WebFluxConfigurer {

    @Bean
    open fun configureSecurity(
        http: ServerHttpSecurity,
        jwtAuthenticationFilter: AuthenticationWebFilter,
        jwtService: JWTService
    ): SecurityWebFilterChain =
        http
            .csrf().disable()
            .logout().disable()
            .authorizeExchange()
            .pathMatchers(*EXCLUDED_PATHS).permitAll()
            .pathMatchers("/admin/**").hasRole(Role.Admin.toString())
            .anyExchange().authenticated()
            .and()
            .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .addFilterAt(JWTAuthorizationFilter(jwtService), SecurityWebFiltersOrder.AUTHORIZATION)
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
            .build()

    @Bean
    open fun mainRouter() = router {
        accept(MediaType.TEXT_HTML).nest {
            GET("/") { temporaryRedirect(URI("/index.html")).build() }
        }
        resources("/**", ClassPathResource("public/"))
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    open fun authenticationWebFilter(
        reactiveAuthenticationManager: ReactiveAuthenticationManager,
        jwtConverter: ServerAuthenticationConverter,
        serverAuthenticationSuccessHandler: ServerAuthenticationSuccessHandler,
        jwtServerAuthenticationFailureHandler: ServerAuthenticationFailureHandler
    ): AuthenticationWebFilter =
        AuthenticationWebFilter(reactiveAuthenticationManager).apply {
            setRequiresAuthenticationMatcher { serverWebExchange ->
                ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/login").matches(serverWebExchange)
            }
            setServerAuthenticationConverter(jwtConverter)
            setAuthenticationSuccessHandler(serverAuthenticationSuccessHandler)
            setAuthenticationFailureHandler(jwtServerAuthenticationFailureHandler)
            setSecurityContextRepository(NoOpServerSecurityContextRepository.getInstance())
        }

    @Bean
    open fun jacksonDecoder(): AbstractJackson2Decoder = Jackson2JsonDecoder()

    @Bean
    open fun reactiveAuthenticationManager(
        authDetailsService: CustomerAuthDetailsService,
        passwordEncoder: PasswordEncoder
    ): ReactiveAuthenticationManager =
        UserDetailsRepositoryReactiveAuthenticationManager(authDetailsService).apply {
            setPasswordEncoder(passwordEncoder)
        }

    companion object {
        val EXCLUDED_PATHS = arrayOf(
            "/v1/ms-docs/**",
            "/v1/auth/",
        )
    }
}