package com.example.bankingappmod.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bankingappmod.customFields.CustomButton
import com.example.bankingappmod.customFields.DropdownMenuWithStatus
import com.example.bankingappmod.customFields.EditableField
import com.example.bankingappmod.customFields.RegularText
import com.example.bankingappmod.data.TransactionItemData
import com.example.bankingappmod.ui.theme.Amount
import com.example.bankingappmod.ui.theme.Okay
import com.example.bankingappmod.ui.theme.TrApplied
import com.example.bankingappmod.ui.theme.TrNumber
import com.example.bankingappmod.ui.theme.TrStatus
import com.example.bankingappmod.ui.theme.Transaction
import com.example.bankingappmod.utils.TransactionStatus
import com.example.bankingappmod.utils.dateFormatter
import com.example.bankingappmod.vm.TransactionsViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTransactionScreen(
    viewModel: TransactionsViewModel = hiltViewModel(),
    onOkayClick: () -> Unit
) {
    var transactionPlace by remember { mutableStateOf("") }
    var transactionNumber by remember { mutableStateOf("") }
    var transactionStatus by remember { mutableStateOf("") }
    var transactionAmount by remember { mutableStateOf("") }
    var amountError by remember { mutableStateOf(false) }

    val isFormValid by derivedStateOf {
        transactionPlace.isNotBlank() &&
                transactionNumber.isNotBlank() &&
                transactionStatus.isNotBlank() &&
                transactionAmount.isNotBlank() &&
                !amountError
    }

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
        EditableField(
            value = transactionPlace,
            onValueChange = { newValue -> transactionPlace = newValue }
        )
        Spacer(modifier = Modifier.height(16.dp))
        RegularText(text = TrNumber)
        Spacer(modifier = Modifier.height(8.dp))
        EditableField(
            value = transactionNumber,
            onValueChange = { newValue -> transactionNumber = newValue }
        )
        Spacer(modifier = Modifier.height(16.dp))
        RegularText(text = TrStatus)
        Spacer(modifier = Modifier.height(8.dp))
        DropdownMenuWithStatus(
            transactionStatus = transactionStatus,
            onStatusSelected = { status -> transactionStatus = status }
        )
        Spacer(modifier = Modifier.height(16.dp))
        RegularText(text = Amount)
        Spacer(modifier = Modifier.height(8.dp))
        EditableField(
            value = transactionAmount,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() || it == '.' }) {
                    transactionAmount = newValue
                    amountError = false
                } else {
                    amountError = true
                }
            },
            isError = amountError
        )
        Spacer(modifier = Modifier.height(32.dp))
        CustomButton(
            onOkayClick = {
                if (isFormValid) {
                    val newTransaction = TransactionItemData(
                        id = 0,
                        transactionPlace = transactionPlace,
                        transactionNumber = transactionNumber,
                        transactionDate = dateFormatter(),
                        transactionStatus = TransactionStatus.valueOf(transactionStatus),
                        transactionAmount = transactionAmount.toFloat()
                    )
                    viewModel.addTransaction(newTransaction)
                    onOkayClick()
                }
            },
            text = Okay
        )
    }
}
