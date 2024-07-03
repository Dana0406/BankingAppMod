package com.example.bankingappmod.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bankingappmod.data.TransactionItemData

@Dao
interface TransactionItemDataDao {
    @Insert
    suspend fun insert(transactionItemData: TransactionItemData)

    @Query("SELECT * FROM transaction_item_data WHERE id = :id")
    suspend fun getTransactionItemDataById(id: Int): TransactionItemData?

    @Query("SELECT * FROM transaction_item_data")
    suspend fun getAllTransactionItemData(): List<TransactionItemData>

    @Update
    suspend fun update(transaction: TransactionItemData)
}