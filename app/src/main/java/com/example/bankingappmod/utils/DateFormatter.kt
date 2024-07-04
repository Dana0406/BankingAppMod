package com.example.bankingappmod.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun dateFormatter(): String {
    val currentDate = LocalDate.now()

    val formatter = DateTimeFormatter.ofPattern("dd.MM.yy")

    return currentDate.format(formatter)
}