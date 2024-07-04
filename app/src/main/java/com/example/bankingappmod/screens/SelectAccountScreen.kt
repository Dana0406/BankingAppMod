package com.example.bankingappmod.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bankingappmod.data.AccountItemData
import com.example.bankingappmod.items.AccountItem
import com.example.bankingappmod.rcViews.AccountRecyclerView
import com.example.bankingappmod.ui.theme.SelectTheAccount
import com.example.bankingappmod.vm.AccountsViewModel

@Composable
fun SelectAccountScreen(
    onSelAccountClick: (AccountItemData) -> Unit,
    onDismiss: () -> Unit,
    accountViewModel: AccountsViewModel = hiltViewModel()
) {
    val accounts by accountViewModel.accounts.observeAsState(emptyList())
    val selectedAccount by accountViewModel.selectedAccount.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Select Account",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        AccountRecyclerView(
            accounts = accounts,
            selectedAccount = selectedAccount,
            onSelectAccountClick = { account ->
                accountViewModel.selectAccount(account)
                onSelAccountClick(account)
            },
            showForwardIcon = false
        )
    }
}
