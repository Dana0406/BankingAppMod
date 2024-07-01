package com.example.bankingappmod.customFields

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.bankingappmod.R

@Composable
fun EditableField(
    value: String,
    showCalendarIcon: Boolean = false,
    modifier: Modifier = Modifier,
    onCalendarIconClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(Color.Black, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .height(32.dp)
            .fillMaxWidth()
    ) {
        BasicTextField(
            value = value,
            onValueChange = {},
            textStyle = TextStyle(color = Color.White),
            cursorBrush = SolidColor(Color.White),
            modifier = Modifier
                .weight(1f)
                .padding(end = if (showCalendarIcon) 0.dp else 8.dp)
        )
        if (showCalendarIcon) {
            Image(
                painter = painterResource(id = R.drawable.calendar),
                contentDescription = "Calendar Icon",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onCalendarIconClick() }
            )
        }
    }
}