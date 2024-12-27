package com.example.janitriassignment.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ColorDao {
    @Insert
    suspend fun insertColor(color: ColorEntity)

    @Query("SELECT * FROM colors")
    suspend fun getAllColors(): List<ColorEntity>

    @Query("SELECT COUNT(*) FROM colors WHERE synced = 0")
    suspend fun getPendingColorCount(): Int

    @Query("UPDATE colors SET synced = :isSynced WHERE id = :id")
    suspend fun updateColorSyncedStatus(id: Int, isSynced: Boolean)

}