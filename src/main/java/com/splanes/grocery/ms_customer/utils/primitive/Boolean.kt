package com.splanes.grocery.ms_customer.utils.primitive

fun Boolean?.orFalse(): Boolean = this ?: false

fun Boolean?.orTrue(): Boolean = this ?: true