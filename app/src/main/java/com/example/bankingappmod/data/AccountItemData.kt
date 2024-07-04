package com.example.bankingappmod.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_item_data")
data class AccountItemData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val accountName: String,
    val accountNumber: String,
    val cardNumber: String
)
