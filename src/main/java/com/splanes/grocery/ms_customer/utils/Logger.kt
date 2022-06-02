package com.splanes.grocery.ms_customer.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun <T> T.loggerOf(): Logger = LoggerFactory.getLogger(this?.let { it::class.java })

fun loggerOf(topic: String = ""): Logger = LoggerFactory.getLogger(topic)