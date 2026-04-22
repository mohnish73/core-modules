package com.binary.mobile.utils

fun Int.padStart(length: Int): String = toString().padStart(length, '0')

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}

fun Long.toReadableSize(): String = when {
    this < 1024        -> "${this} B"
    this < 1024 * 1024 -> "${(this / 1024)} KB"
    else               -> "${(this / (1024 * 1024))} MB"
}
