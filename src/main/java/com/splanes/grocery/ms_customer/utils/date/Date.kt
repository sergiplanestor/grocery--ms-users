package com.splanes.grocery.ms_customer.utils.date

import java.util.*

fun timestamp(): Long = System.currentTimeMillis()

fun now(): Date = Date(timestamp())

fun Date.add(millis: Int): Date =
    Calendar.getInstance().apply {
        time = this@add
        add(Calendar.MILLISECOND, millis)
    }.time

fun dateOf(millis: Long?, default: Long = timestamp()): Date =
    Date(millis ?: default)