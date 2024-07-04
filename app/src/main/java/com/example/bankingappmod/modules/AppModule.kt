package com.example.bankingappmod.modules

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bankingappmod.data.AccountItemData
import com.example.bankingappmod.data.TransactionItemData
import com.example.bankingappmod.db.AccountItemDataDao
import com.example.bankingappmod.db.AppDatabase
import com.example.bankingappmod.db.TransactionItemDataDao
import com.example.bankingappmod.utils.TransactionStatus
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "bank_database1")
            .addCallback(object : RoomDatabase.Callback() {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    CoroutineScope(Dispatchers.IO).launch {
                        val initialAccount = AccountItemData(
                            accountName = "Main Account",
                            accountNumber = "1234567890",
                            cardNumber = "1111222233334444"
                        )
                        val initialAccount2 = AccountItemData(
                            accountName = "Main Account",
                            accountNumber = "1234567890",
                            cardNumber = "1111222233334444"
                        )

                        val initialTransactions = listOf(
                            TransactionItemData(
                                transactionPlace = "Grocery Store",
                                transactionNumber = "T001",
                                transactionDate = "25.07.2024",
                                transactionStatus = TransactionStatus.EXECUTED,
                                transactionAmount = 50.0f
                            ),
                            TransactionItemData(
                                transactionPlace = "Online Shopping",
                                transactionNumber = "T002",
                                transactionDate = "25.07.2024",
                                transactionStatus = TransactionStatus.EXECUTED,
                                transactionAmount = 150.0f
                            )
                        )

                        provideDatabase(app).accountItemDataDao().insert(initialAccount)
                        provideDatabase(app).accountItemDataDao().insert(initialAccount2)
                        provideDatabase(app).transactionItemDataDao().insert(initialTransactions[0])
                        provideDatabase(app).transactionItemDataDao().insert(initialTransactions[1])
                    }
                }
            })
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    fun provideTransactionItemDataDao(db: AppDatabase): TransactionItemDataDao {
        return db.transactionItemDataDao()
    }

    @Provides
    fun provideAccountItemDataDao(db: AppDatabase): AccountItemDataDao {
        return db.accountItemDataDao()
    }
}