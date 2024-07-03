package com.example.bankingappmod.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankingappmod.data.AccountItemData
import com.example.bankingappmod.db.AccountItemDataDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val accountDao: AccountItemDataDao
) : ViewModel() {

    private val _accounts = MutableLiveData<List<AccountItemData>>()
    val accounts: LiveData<List<AccountItemData>> = _accounts

    init {
        loadAccounts()
    }

    private fun loadAccounts() {
        viewModelScope.launch {
            _accounts.value = accountDao.getAllAccountItemData()
        }
    }

    fun addAccount(account: AccountItemData) {
        viewModelScope.launch {
            accountDao.insert(account)
            loadAccounts()
        }
    }
}