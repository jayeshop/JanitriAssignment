package com.example.janitriassignment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.janitriassignment.data.db.ColorEntity
import com.example.janitriassignment.repository.ColorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ColorViewModel(private val repository: ColorRepository) : ViewModel() {

    private val _colors = MutableStateFlow<List<ColorEntity>>(emptyList())
    val colors = _colors.asStateFlow()

    private val _pendingColorCount = MutableStateFlow<Int>(0)
    val pendingColorCount = _pendingColorCount.asStateFlow()

    suspend fun getAllColors() {
        _colors.value = repository.getAllColors()
        _pendingColorCount.value = repository.getPendingColorCount()
    }

    fun addColor(color: ColorEntity) {
        viewModelScope.launch {
            repository.insertColor(color)
            refreshColors()
        }
    }

    fun syncColors() {
        viewModelScope.launch {
            val unsyncedColors = repository.getAllColors().filter { !it.synced }
            if (unsyncedColors.isNotEmpty()) {
                unsyncedColors.forEach { color ->
                    repository.syncColorToFirebase(color)
                    repository.updateColorSyncedStatus(color.id, true)
                }
                refreshColors()
            }
        }
    }

    private fun refreshColors() {
        viewModelScope.launch {
            _colors.value = repository.getAllColors()
            _pendingColorCount.value = repository.getPendingColorCount()
        }
    }

}