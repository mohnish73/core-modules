package com.binary.mobile.utils

fun <T> List<T>?.orEmpty(): List<T> = this ?: emptyList()

fun <T> List<T>.second(): T = this[1]

fun <K, V> Map<K, V>?.orEmpty(): Map<K, V> = this ?: emptyMap()
