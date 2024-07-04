package com.example.bankingappmod.rcViews

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bankingappmod.data.TransactionItemData
import com.example.bankingappmod.items.TransactionItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecentTransactionsRecyclerView(
    onTransactionClick: (Int) -> Unit,
    transactions: List<TransactionItemData>
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(transactions) { transaction ->
            TransactionItem(
                onTransDetailClick = { onTransactionClick(transaction.id) },
                transactionItem = transaction
            )
        }
    }
}



