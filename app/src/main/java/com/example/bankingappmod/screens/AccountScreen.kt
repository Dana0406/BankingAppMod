package com.example.bankingappmod.screens


import android.accounts.Account
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankingappmod.R
import com.example.bankingappmod.rcViews.AccountRecyclerView
import com.example.bankingappmod.rcViews.RecentTransactionsRecyclerView
import com.example.bankingappmod.ui.theme.Account
import com.example.bankingappmod.ui.theme.Add
import com.example.bankingappmod.ui.theme.Blue
import com.example.bankingappmod.ui.theme.RecentTransactions
import com.example.bankingappmod.ui.theme.ViewAll

@Composable
fun AccountScreen(
    onSelectAccountClick: () -> Unit,
    onViewAllClick: () -> Unit,
    onTransactionClick: () -> Unit,
    onAddClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = Account,
            fontSize = 28.sp,
            color = Color.White,
            modifier = Modifier.padding(top = 12.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        AccountRecyclerView(onSelectAccountClick)
        Spacer(modifier = Modifier.height(23.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = RecentTransactions,
                fontSize = 28.sp,
                color = Color.White
            )
            Text(
                text = ViewAll,
                fontSize = 13.sp,
                color = Blue,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { onViewAllClick() }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
        ) {
            RecentTransactionsRecyclerView(onTransactionClick)
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .size(48.dp)
                .background(color = Blue, shape = CircleShape)
                .align(Alignment.End)
        ) {
            IconButton(
                onClick = onAddClick,
                modifier = Modifier.padding(12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = Add,
                    tint = Color.White
                )
            }
        }
    }
}

