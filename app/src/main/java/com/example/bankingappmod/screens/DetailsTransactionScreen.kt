package com.example.bankingappmod.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankingappmod.customFields.CustomButton
import com.example.bankingappmod.customFields.EditableField
import com.example.bankingappmod.customFields.RegularText
import com.example.bankingappmod.data.TransactionItemData
import com.example.bankingappmod.ui.theme.Amount
import com.example.bankingappmod.ui.theme.Date
import com.example.bankingappmod.ui.theme.TrApplied
import com.example.bankingappmod.ui.theme.TrNumber
import com.example.bankingappmod.ui.theme.TrStatus
import com.example.bankingappmod.ui.theme.Transaction

@Composable
fun DetailsTransactionScreen(
    transactionItemData: TransactionItemData,
    onOkayClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp)
    ) {
        Text(
            text = Transaction,
            fontSize = 28.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(32.dp))
        RegularText(text = TrApplied)
        Spacer(modifier = Modifier.height(8.dp))
        EditableField(value = transactionItemData.transactionPlace)
        Spacer(modifier = Modifier.height(16.dp))
        RegularText(text = TrNumber)
        Spacer(modifier = Modifier.height(8.dp))
        EditableField(value = transactionItemData.transactionNumber)
        Spacer(modifier = Modifier.height(16.dp))
        RegularText(text = Date)
        Spacer(modifier = Modifier.height(8.dp))
        EditableField(value = transactionItemData.transactionDate.toString())
        Spacer(modifier = Modifier.height(16.dp))
        RegularText(text = TrStatus)
        Spacer(modifier = Modifier.height(8.dp))
        EditableField(value = transactionItemData.transactionStatus.toString())
        Spacer(modifier = Modifier.height(16.dp))
        RegularText(text = Amount)
        Spacer(modifier = Modifier.height(8.dp))
        EditableField(value = "$${transactionItemData.transactionAmount}")
        Spacer(modifier = Modifier.height(32.dp))
        CustomButton(onOkayClick = onOkayClick, text = "Okay")
    }
}