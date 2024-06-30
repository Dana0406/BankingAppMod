package com.example.bankingappmod.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import com.example.bankingappmod.data.AccountItemData
import com.example.bankingappmod.ui.theme.BackgroundGrey
import com.example.bankingappmod.ui.theme.TextGrey

@Composable
fun AccountItem(
    onSelectAccountClick: () -> Unit,
    accountItem: AccountItemData,
    showForwardIcon: Boolean = true
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundGrey)
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 16.dp)
                    .background(Color.Green)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = accountItem.accountName,
                    fontSize = 15.sp,
                    color = Color.White
                )
                Text(
                    text = accountItem.accountNumber,
                    fontSize = 13.sp,
                    color = TextGrey
                )
                Text(
                    text = accountItem.cardNumber,
                    fontSize = 13.sp,
                    color = TextGrey
                )
            }
            if (showForwardIcon) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Top)
                        .clickable { onSelectAccountClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.forward),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}