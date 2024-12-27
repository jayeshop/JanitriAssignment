package com.example.janitriassignment.ui

import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType.Companion.Sp
import androidx.compose.ui.unit.dp
import com.example.janitriassignment.data.db.ColorEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ColorCard(colorEntity: ColorEntity) {
    Card(
        modifier = Modifier
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(
            modifier = Modifier.background(Color(parseColor(colorEntity.color)))
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 10.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = colorEntity.color,
                        color = Color.White,
                        fontSize = TextUnit(18f, Sp)
                    )
                    Divider(
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(0.6f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "Created at",
                            color = Color.White,
                        )
                        Text(
                            text = TimestampToFormattedDate(colorEntity.timestamp),
                            color = Color.White,
                        )
                    }
                }
            }
        }
    }
}

fun TimestampToFormattedDate(timestamp: Long): String{
    val date = Date(timestamp)
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(date)
}