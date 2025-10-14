package com.example.vasilkhusainovtest.common

import android.annotation.SuppressLint
import com.example.vasilkhusainovtest.domain.models.Type
import com.example.vasilkhusainovtest.domain.models.Type.File
import com.example.vasilkhusainovtest.domain.models.Type.Folder
import com.example.vasilkhusainovtest.domain.models.Type.Unknown
import java.text.DecimalFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.ln
import kotlin.math.pow

const val BYTE_SCALE = 1024.0

fun formatCount(count: Int?): String {

    if (count == null || count < 0) {
        return "0"
    }

    if (count < 1000) {
        return count.toString()
    }

    val countInKilos = count / 1000.0

    val formatter = DecimalFormat("#.#")

    return "${formatter.format(countInKilos)}k"
}

fun convertIsoToCustomFormat(isoDateTime: String?): String? {
    val targetFormat = "dd.MM.yyyy HH:mm:ss"

    val formatter = DateTimeFormatter.ofPattern(targetFormat, Locale.getDefault())

    val instant = try {
        Instant.parse(isoDateTime)
    } catch (_: Exception) {
        return null
    }

    val localDateTime = instant.atZone(ZoneId.systemDefault())

    return localDateTime.format(formatter)
}

fun fromStringToTypeObject(typeString: String?): Type {
    return when (typeString?.lowercase()) {
        "file" -> File
        "dir" -> Folder
        else -> Unknown
    }
}


@SuppressLint("DefaultLocale")
fun Int.formatBytes(): String {
    val bytes = this.toLong()

    if (bytes < BYTE_SCALE) {
        return "$bytes B"
    }

    val units = arrayOf("KB", "MB", "GB", "TB", "PB", "EB")

    val exp = (ln(bytes.toDouble()) / ln(BYTE_SCALE)).toInt()

    val value = bytes.toDouble() / BYTE_SCALE.pow(exp.toDouble())

    val formattedValue = String.format("%.1f %s", value, units[exp - 1])

    val valueWithComma = formattedValue.replace('.', ',')

    return valueWithComma
}