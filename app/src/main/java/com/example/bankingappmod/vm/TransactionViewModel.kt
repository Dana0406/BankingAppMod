package com.example.bankingappmod.vm

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankingappmod.data.TransactionItemData
import com.example.bankingappmod.db.TransactionItemDataDao
import com.example.bankingappmod.utils.createFlexibleDateFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val transactionDao: TransactionItemDataDao
) : ViewModel() {

    private val _transactions = MutableLiveData<List<TransactionItemData>>()
    val transactions: LiveData<List<TransactionItemData>> = _transactions

    private val _selectedTransaction = MutableLiveData<TransactionItemData?>()
    val selectedTransaction: LiveData<TransactionItemData?> = _selectedTransaction

    private val _filteredTransactions = MutableLiveData<List<TransactionItemData>>()
    val filteredTransactions: LiveData<List<TransactionItemData>> = _filteredTransactions

    @RequiresApi(Build.VERSION_CODES.O)
    private val dateFormatter = createFlexibleDateFormatter()

    init {
        loadTransactions()
    }

    private fun loadTransactions() {
        viewModelScope.launch {
            val allTransactions = transactionDao.getAllTransactionItemData()
            _transactions.value = allTransactions
            _filteredTransactions.value = allTransactions
        }
    }

    fun selectTransaction(transactionId: Int) {
        viewModelScope.launch {
            _selectedTransaction.value = transactionDao.getTransactionItemDataById(transactionId)
        }
    }

    fun updateTransaction(transaction: TransactionItemData) {
        viewModelScope.launch {
            transactionDao.update(transaction)
            loadTransactions()
        }
    }

    fun addTransaction(transaction: TransactionItemData) {
        viewModelScope.launch {
            transactionDao.insert(transaction)
            loadTransactions()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun filterAndSortTransactions(startDate: String, endDate: String) {
        try {
            val startDateParsed = LocalDate.parse(startDate, dateFormatter)
            val endDateParsed = LocalDate.parse(endDate, dateFormatter)

            val allTransactions = _transactions.value ?: emptyList()

            val filteredList = allTransactions.filter { transaction ->
                val transactionDate = LocalDate.parse(transaction.transactionDate, dateFormatter)
                transactionDate in startDateParsed..endDateParsed
            }.sortedByDescending { LocalDate.parse(it.transactionDate, dateFormatter) }

            Log.e("TransactionsViewModel", "Error parsing dates: $filteredList")
            _filteredTransactions.value = filteredList
            _transactions.value = filteredList
        } catch (e: Exception) {
            Log.e("TransactionsViewModel", "Error parsing dates: ${e.message}")
        }
    }
}
