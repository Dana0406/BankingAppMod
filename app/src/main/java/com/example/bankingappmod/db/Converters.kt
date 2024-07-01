package com.example.bankingappmod.db

import androidx.room.TypeConverter
import com.example.bankingappmod.utils.TransactionStatus

class Converters {
    @TypeConverter
    fun fromTransactionStatus(status: TransactionStatus): String {
        return status.name
    }
    @TypeConverter
    fun toTransactionStatus(status: String): TransactionStatus {
        return TransactionStatus.valueOf(status)
    }

}