package com.example.bankingappmod.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.bankingappmod.customFields.CalendarDialog
import com.example.bankingappmod.customFields.CustomButton
import com.example.bankingappmod.customFields.EditableField
import com.example.bankingappmod.customFields.RegularText

@Composable
fun FilterScreen(
    onSubmitClick: () -> Unit
) {
    var showCalendar by remember { mutableStateOf(false) }
    var selectedStartDate by remember { mutableStateOf("") }
    var selectedEndDate by remember { mutableStateOf("") }
    var isStartDate by remember { mutableStateOf(true) }

    if (showCalendar) {
        CalendarDialog(
            onDismiss = { showCalendar = false },
            onDateSelected = { date ->
                if (isStartDate) {
                    selectedStartDate = date
                } else {
                    selectedEndDate = date
                }
                showCalendar = false
            }
        )
    }

    Dialog(onDismissRequest = { onSubmitClick() }) {
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.Black, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp, vertical = 24.dp)
                    .wrapContentSize()
            ) {
                Text(
                    text = "Filter by date",
                    color = Color.White,
                    fontSize = 34.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    RegularText(text = "Start Date")
                    Spacer(modifier = Modifier.height(8.dp))
                    EditableField(selectedStartDate, showCalendarIcon = true, onCalendarIconClick = {
                        isStartDate = true
                        showCalendar = true
                    })
                    Spacer(modifier = Modifier.height(16.dp))
                    RegularText(text = "End Date")
                    Spacer(modifier = Modifier.height(8.dp))
                    EditableField(selectedEndDate, showCalendarIcon = true, onCalendarIconClick = {
                        isStartDate = false
                        showCalendar = true
                    })
                    Spacer(modifier = Modifier.height(24.dp))
                    CustomButton(
                        "Submit",
                        onSubmitClick,
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}