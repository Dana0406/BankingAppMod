package com.example.bankingappmod.data

import com.example.bankingappmod.utils.TransactionStatus
import java.util.Date

data class TransactionItemData(
    val transactionPlace: String,
    val transactionNumber: String,
    val transactionDate: String,
    val transactionStatus: TransactionStatus,
    val transactionAmount: Float
    )
