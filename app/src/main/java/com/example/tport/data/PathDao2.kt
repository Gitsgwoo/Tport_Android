package com.example.tport.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PathDao2 {
    @Insert
    suspend fun insertPath2(path: Path2)
    @Update
    suspend fun updatePath2(path: Path2)
    @Delete
    suspend fun deletePath2(path: Path2)
    @Query("SELECT * from path_table_2 WHERE id = :id")
    fun getPath2(id: Int): Flow<Path2>
    @Query("SELECT * from path_table_2")
    fun getPathList2(): Flow<List<Path2>>
    @Query("SELECT * from path_table_2 WHERE origin = :origin AND destination = :destination AND searchTime = :searchTime")
    fun getSearchedPathList2(origin: String, destination: String, searchTime: String): Flow<List<Path2>>
    @Query("Update path_table_2 SET reservedNum = reservedNum+1, emptyNum = emptyNum-1, waitingNum = waitingNum+1 WHERE id = :id")
    suspend fun updateReservedNum2(id: Int)
}