package com.example.tport.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PathDao {
    @Insert
    suspend fun insertPath(path: Path)
    @Update
    suspend fun updatePath(path: Path)
    @Delete
    suspend fun deletePath(path: Path)
    @Query("SELECT * from path_table WHERE id = :id")
    fun getPath(id: Int): Flow<Path>
    @Query("SELECT * from path_table")
    fun getPathList(): Flow<List<Path>>
    @Query("SELECT * from path_table WHERE origin = :origin AND destination = :destination AND searchTime = :searchTime")
    fun getSearchedPathList(origin: String, destination: String, searchTime: String): Flow<List<Path>>
    @Query("SELECT * from path_table WHERE origin = :origin AND destination = :destination AND searchTime = :searchTime ORDER BY tportTravelTime ASC")
    fun getTportSearchedPathList(origin: String, destination: String, searchTime: String): Flow<List<Path>>
    @Query("Update path_table SET reservedNum = reservedNum+1, emptyNum = emptyNum-1, waitingNum = waitingNum+1 WHERE id = :id")
    suspend fun updateReservedNum(id: Int)
}