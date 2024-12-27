package com.example.janitriassignment.repository

import com.example.janitriassignment.data.db.ColorDao
import com.example.janitriassignment.data.db.ColorEntity
import com.google.firebase.database.FirebaseDatabase

class ColorRepository(private val colorDao: ColorDao) {

    suspend fun insertColor(color: ColorEntity) = colorDao.insertColor(color)

    suspend fun getAllColors() = colorDao.getAllColors()

    suspend fun getPendingColorCount() = colorDao.getPendingColorCount()

    suspend fun updateColorSyncedStatus(id: Int, isSynced: Boolean) {
        colorDao.updateColorSyncedStatus(id, isSynced)
    }

    suspend fun syncColorToFirebase(color: ColorEntity) {
        val databaseRef = FirebaseDatabase.getInstance().getReference("colors")
        databaseRef.push().setValue(color.copy(synced = true))
    }
}