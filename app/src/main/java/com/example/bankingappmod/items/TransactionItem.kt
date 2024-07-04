package com.example.bankingappmod.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
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
import com.example.bankingappmod.data.TransactionItemData
import com.example.bankingappmod.ui.theme.BackgroundGrey
import com.example.bankingappmod.ui.theme.TextGrey

@Composable
fun TransactionItem(
    onTransDetailClick: () -> Unit,
    transactionItem: TransactionItemData
) {
    Column {
        Card(
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundGrey)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = transactionItem.transactionPlace,
                        fontSize = 17.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = transactionItem.transactionDate.toString(),
                        fontSize = 13.sp,
                        color = TextGrey
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = transactionItem.transactionStatus.toString(),
                        fontSize = 13.sp,
                        color = TextGrey
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${transactionItem.transactionAmount}$",
                        fontSize = 17.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Box(
                        modifier = Modifier.clickable { onTransDetailClick() }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.forward),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(0.2.dp))
        Divider(
            color = TextGrey,
            modifier = Modifier
                .fillMaxWidth()
                .height(0.2.dp)
                .padding(horizontal = 16.dp)
                .background(TextGrey)
        )
    }
}