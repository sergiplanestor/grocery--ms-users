package com.splanes.grocery.ms_customer.utils

fun <T> Result<T>.getOrThrow(err: Throwable): T =
    getOrThrow { err }

fun <T> Result<T>.getOrThrow(err: (Throwable) -> Throwable): T =
    getOrElse { throw err(it) }