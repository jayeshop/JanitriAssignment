package com.example.janitriassignment.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType.Companion.Sp
import androidx.compose.ui.unit.dp
import com.example.janitriassignment.R
import com.example.janitriassignment.data.db.ColorEntity
import kotlinx.coroutines.launch
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: ColorViewModel) {
    val colors = viewModel.colors.collectAsState(initial = emptyList())
    val pendingColorCount = viewModel.pendingColorCount.collectAsState(initial = 0)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getAllColors()
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color(0xFF5659A4),
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    scrolledContainerColor = Color(0xFF5659A4)
                ),
                title = {
                    Text("Color App")
                },
                actions = {
                    Button(onClick = {
                        coroutineScope.launch {
                            viewModel.syncColors()
                        }
                    },
                        modifier = Modifier.padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFB6B9FF)
                        )
                    ) {
                        Text(pendingColorCount.value.toString(),
                            fontSize = TextUnit(20f, Sp),
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sync),
                            contentDescription = "Sync",
                            tint = Color(0xFF5659A4),
                            modifier = Modifier.graphicsLayer(scaleX = -1f)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onAddColor = {
                coroutineScope.launch {
                    viewModel.addColor(
                        ColorEntity(color = generateRandomColor(), timestamp = System.currentTimeMillis())
                    )
                }
            })
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(colors.value) { color ->
                ColorCard(colorEntity = color)
            }
        }
    }
}

fun generateRandomColor(): String {
    val random = Random
    val color = String.format("#%06X", 0xFFFFFF and random.nextInt(0x1000000))
    return color
}