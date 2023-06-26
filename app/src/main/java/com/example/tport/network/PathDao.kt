package com.example.tport.network

import androidx.room.*
import com.example.tport.network.dto.previous.Path0
import kotlinx.coroutines.flow.Flow

@Dao
interface PathDao {
    @Insert
    suspend fun insertPath(path: Path0)
    @Update
    suspend fun updatePath(path: Path0)
    @Delete
    suspend fun deletePath(path: Path0)
    @Query("SELECT * from path_table WHERE id = :id")
    fun getPath(id: Int): Flow<Path0>
    @Query("SELECT * from path_table")
    fun getPathList(): Flow<List<Path0>>
    @Query("SELECT * from path_table WHERE origin = :origin AND destination = :destination AND searchTime = :searchTime")
    fun getSearchedPathList(origin: String, destination: String, searchTime: String): Flow<List<Path0>>
    @Query("SELECT * from path_table WHERE origin = :origin AND destination = :destination AND searchTime = :searchTime ORDER BY tportTravelTime ASC")
    fun getTportSearchedPathList(origin: String, destination: String, searchTime: String): Flow<List<Path0>>
    @Query("Update path_table SET reservedNum = reservedNum+1, emptyNum = emptyNum-1, waitingNum = waitingNum+1 WHERE id = :id")
    suspend fun updateReservedNum(id: Int)
}