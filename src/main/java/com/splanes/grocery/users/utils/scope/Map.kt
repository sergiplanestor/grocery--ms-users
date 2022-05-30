package com.splanes.grocery.users.utils.primitive

fun <T : Any, R : Any> T.mapIfOrElse(predicate: Boolean, default: R, mapper: (T) -> R) : R =
    if (predicate) {
        mapper(this)
    } else {
        default
    }

fun <T : Any> T.mapIf(predicate: Boolean, mapper: (T) -> T) : T =
    mapIfOrElse(predicate, default = this, mapper)

fun <T : Any> T?.mapIfNotNull(mapper: (T) -> T) : T? =
    this?.run(mapper)