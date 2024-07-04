package com.example.bankingappmod.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

@RequiresApi(Build.VERSION_CODES.O)
fun dateFormatter(): String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yy")
    return currentDate.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun createFlexibleDateFormatter(): DateTimeFormatter {
    return DateTimeFormatterBuilder()
        .appendOptional(DateTimeFormatter.ofPattern("d.M.yyyy"))
        .appendOptional(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        .appendOptional(DateTimeFormatter.ofPattern("dd.M.yyyy"))
        .appendOptional(DateTimeFormatter.ofPattern("d.MM.yyyy"))
        .toFormatter()
}