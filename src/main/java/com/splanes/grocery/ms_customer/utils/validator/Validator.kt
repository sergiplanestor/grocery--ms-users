package com.splanes.grocery.ms_customer.utils.validator

import javax.validation.ConstraintViolation
import javax.validation.ValidationException
import javax.validation.Validator

const val PATTERN_EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
const val PATTERN_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$"

fun <T> T.validate(validator: Validator): List<ConstraintViolation<T>> =
    validator.validate(this).toList()

fun <T> T.isValid(validator: Validator): Boolean =
    validate(validator).isEmpty()

fun <T> T.validateOrThrow(validator: Validator): T =
    with(validate(validator)) {
        if (isEmpty()) {
            this@validateOrThrow
        } else {
            throw ValidationException(first().message)
        }
    }
