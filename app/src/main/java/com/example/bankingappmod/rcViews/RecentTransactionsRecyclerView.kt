package com.example.bankingappmod.rcViews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bankingappmod.data.TransactionItemData
import com.example.bankingappmod.items.TransactionItem
import com.example.bankingappmod.utils.TransactionStatus
import com.example.bankingappmod.utils.dateFormatter
import java.util.Date
@Composable
fun RecentTransactionsRecyclerView(onTransactionClick: () -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(4) {
            TransactionItem(
                onTransactionClick,
                TransactionItemData("11", "11", dateFormatter(), TransactionStatus.EXECUTED, 10.02f)
            )
        }
    }
}



