package com.splanes.grocery.ms_customer.utils.primitive

import java.util.*

fun String.capitalize(): String =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }