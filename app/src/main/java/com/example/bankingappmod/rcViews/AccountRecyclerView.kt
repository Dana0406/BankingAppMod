package com.example.bankingappmod.rcViews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bankingappmod.data.AccountItemData
import com.example.bankingappmod.items.AccountItem
import com.example.bankingappmod.vm.AccountsViewModel

@Composable
fun AccountRecyclerView(
    viewModel: AccountsViewModel = hiltViewModel(),
    onSelectAccountClick: () -> Unit,
    showForwardIcon: Boolean = true
) {
    val accounts by viewModel.accounts.observeAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(accounts) { account ->
            AccountItem(
                onSelectAccountClick,
                account,
                showForwardIcon
            )
        }
    }
}