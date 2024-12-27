package com.example.janitriassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.room.Room
import com.example.janitriassignment.data.db.AppDatabase
import com.example.janitriassignment.repository.ColorRepository
import com.example.janitriassignment.ui.ColorViewModel
import com.example.janitriassignment.ui.MainScreen
import com.example.janitriassignment.ui.theme.JanitriAssignmentTheme
import com.google.firebase.Firebase
import com.google.firebase.initialize


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Firebase.initialize(this)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "color_db").build()
        val repository = ColorRepository(db.colorDao())

        setContent {
            JanitriAssignmentTheme {
                MainScreen(viewModel = ColorViewModel(repository))
            }
        }
    }
}
