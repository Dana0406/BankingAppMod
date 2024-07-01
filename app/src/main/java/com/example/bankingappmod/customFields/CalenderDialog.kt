package com.example.bankingappmod.customFields

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog

@Composable
fun CalendarDialog(onDismiss: () -> Unit, onDateSelected: (String) -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .background(Color.DarkGray, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
            ) {
                AndroidView(
                    factory = { context ->
                        CalendarView(context).apply {
                            setBackgroundColor(android.graphics.Color.DKGRAY)
                            setOnDateChangeListener { _, year, month, dayOfMonth ->
                                val selectedDate = "$dayOfMonth.${month + 1}.$year"
                                onDateSelected(selectedDate)
                                onDismiss()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}