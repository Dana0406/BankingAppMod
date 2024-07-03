package com.example.bankingappmod.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankingappmod.data.TransactionItemData
import com.example.bankingappmod.db.TransactionItemDataDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val transactionDao: TransactionItemDataDao
) : ViewModel() {

    private val _transactions = MutableLiveData<List<TransactionItemData>>()
    val transactions: LiveData<List<TransactionItemData>> = _transactions
    init {
        loadTransactions()
    }

    private fun loadTransactions() {
        viewModelScope.launch {
            _transactions.value = transactionDao.getAllTransactionItemData()
        }
    }

    fun addTransaction(transaction: TransactionItemData) {
        viewModelScope.launch {
            transactionDao.insert(transaction)
            loadTransactions()
        }
    }
}