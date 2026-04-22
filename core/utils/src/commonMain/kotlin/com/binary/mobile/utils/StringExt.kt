package com.binary.mobile.utils

fun String.isValidEmail(): Boolean = matches(Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))

fun String.truncate(maxLength: Int, suffix: String = "..."): String =
    if (length > maxLength) take(maxLength) + suffix else this

fun String.capitalizeFirst(): String =
    if (isEmpty()) this else first().uppercaseChar() + substring(1)

fun String?.orDefault(default: String = ""): String = this ?: default

fun String.isValidPhone(): Boolean = matches(Regex("^[+]?[0-9]{10,13}$"))
