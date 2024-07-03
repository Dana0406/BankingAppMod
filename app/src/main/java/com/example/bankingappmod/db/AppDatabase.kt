package com.example.bankingappmod.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bankingappmod.data.AccountItemData
import com.example.bankingappmod.data.TransactionItemData
@Database(entities = [AccountItemData::class, TransactionItemData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountItemDataDao(): AccountItemDataDao
    abstract fun transactionItemDataDao(): TransactionItemDataDao
}