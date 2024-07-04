package com.example.bankingappmod.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bankingappmod.data.AccountItemData
@Dao
interface AccountItemDataDao {

    @Insert
    suspend fun insert(accountItemData: AccountItemData)

    @Query("SELECT * FROM account_item_data WHERE id = :id")
    suspend fun getAccountItemDataById(id: Int): AccountItemData?

    @Query("SELECT * FROM account_item_data")
    suspend fun getAllAccountItemData(): List<AccountItemData>

}