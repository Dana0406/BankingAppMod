package com.example.bankingappmod.customFields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bankingappmod.R
import com.example.bankingappmod.ui.theme.DropdownIcon

@Composable
fun DropdownMenuWithStatus(
    transactionStatus: String,
    onStatusSelected: (String) -> Unit
) {
    var isDropdownMenuVisible by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .background(Color.Black, shape = RoundedCornerShape(8.dp))
                .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .height(32.dp)
                .fillMaxWidth()
                .clickable(onClick = { isDropdownMenuVisible = !isDropdownMenuVisible })
        ) {
            Text(
                text = transactionStatus,
                color = Color.White,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterStart)
            )
            Icon(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = DropdownIcon,
                tint = Color.White,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
        DropdownMenu(
            expanded = isDropdownMenuVisible,
            onDismissRequest = { isDropdownMenuVisible = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
        ) {
            listOf("DECLINED", "EXECUTED", "IN_PROGRESS").forEach { status ->
                DropdownMenuItem(onClick = {
                    onStatusSelected(status)
                    isDropdownMenuVisible = false
                }) {
                    Text(
                        text = status,
                        color = Color.White
                    )
                }
            }
        }
    }
}