package com.example.janitriassignment.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType.Companion.Sp
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActionButton(onAddColor: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = onAddColor,
        shape = RoundedCornerShape(50)
    ){
            Text("Add Color",
                color = Color(0xFF5659A4),
                fontSize = TextUnit(18f, Sp)
            )
            Spacer(
                modifier = Modifier.width(4.dp)
            )
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Add Color",
                tint = Color(0xFF5659A4),
                modifier = Modifier.size(32.dp)
            )
        }
}