package com.splanes.grocery.ms_customer.application.api.v1.auth.controller

import com.splanes.grocery.ms_customer.domain.service.CustomerService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth")
class AuthController(
    private val service: CustomerService
) {



}