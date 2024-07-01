package com.example.bankingappmod.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.bankingappmod.db.Converters
import com.example.bankingappmod.utils.TransactionStatus

@Entity(tableName = "transaction_item_data")
@TypeConverters(Converters::class)
data class TransactionItemData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val transactionPlace: String,
    val transactionNumber: String,
    val transactionDate: String,
    val transactionStatus: TransactionStatus,
    val transactionAmount: Float
    )
