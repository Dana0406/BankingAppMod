package com.example.bankingappmod.rcViews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bankingappmod.data.AccountItemData
import com.example.bankingappmod.items.AccountItem

@Composable
fun AccountRecyclerView(
    onSelectAccountClick: () -> Unit,
    showForwardIcon: Boolean = true
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(1) {
            if (showForwardIcon) AccountItem(
                onSelectAccountClick,
                AccountItemData(
                    0,
                    "Saving Account",
                    "91212192291221",
                    "1234"
                ), true
            )
            else AccountItem(
                onSelectAccountClick,
                AccountItemData(
                    0,
                    "Saving Account",
                    "91212192291221",
                    "1234"
                ), false
            )
        }
    }
}