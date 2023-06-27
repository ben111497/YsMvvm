package com.example.mvvm.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NumberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: YsNumber)

    @Query("SELECT * FROM $Table_Number WHERE userID LIKE :userID")
    fun getByUserID(userID: String): Flow<YsNumber?>

    @Query("DELETE FROM $Table_Number WHERE userID LIKE :userID")
    suspend fun delete(userID: String): Int
}