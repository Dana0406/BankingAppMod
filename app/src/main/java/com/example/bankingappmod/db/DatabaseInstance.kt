package com.example.bankingappmod.db

import android.content.Context
import androidx.room.Room

object DatabaseInstance {

    private var INSTANCE: AppDatabase? = null
    fun getDatabase(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
            }
        }
        return INSTANCE!!
    }

}