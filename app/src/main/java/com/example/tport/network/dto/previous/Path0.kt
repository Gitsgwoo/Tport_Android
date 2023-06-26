package com.example.tport.network.dto.previous

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "path_table")
data class Path0 (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    var origin: String = "", //출발지
    @ColumnInfo
    val destination: String = "", //도착지
    @ColumnInfo
    val searchTime: String = "", //출발 시간
    @ColumnInfo
    val fare: String = "", //요금
    @ColumnInfo
    val naverTravelTime: Int = -1, //기존 걸리는 시간
    @ColumnInfo
    val tportTravelTime: Int = -1, //보정 후 걸리는 시간
    @ColumnInfo
    val naverArrivalTime: Int = -1, //기존 도착 시간
    @ColumnInfo
    val tportArrivalTime: Int = -1, //보정 후 도착 시간
    @ColumnInfo
    val method1: String = "", //수단1
    @ColumnInfo
    val startPoint1: String = "", //출발지1
    @ColumnInfo
    val endPoint1: String = "", //도착지1
    @ColumnInfo
    val travelTime1: String = "", //걸리는 시간1
    @ColumnInfo
    val method2: String = "", //수단2
    @ColumnInfo
    val startPoint2: String = "", //출발지2
    @ColumnInfo
    val endPoint2: String = "", //도착지2
    @ColumnInfo
    val travelTime2: String = "", //걸리는 시간2
    @ColumnInfo
    val method3: String = "", //수단3
    @ColumnInfo
    val startPoint3: String = "", //출발지3
    @ColumnInfo
    val endPoint3: String = "", //도착지3
    @ColumnInfo
    val travelTime3: String = "", //걸리는 시간3
    @ColumnInfo
    val method4: String = "", //수단4
    @ColumnInfo
    val startPoint4: String = "", //출발지4
    @ColumnInfo
    val endPoint4: String = "", //도착지4
    @ColumnInfo
    val travelTime4: String = "", //걸리는 시간4
    @ColumnInfo
    val method5: String = "", //수단5
    @ColumnInfo
    val startPoint5: String = "", //출발지5
    @ColumnInfo
    val endPoint5: String = "", //도착지5
    @ColumnInfo
    val travelTime5: String = "", //걸리는 시간5
    @ColumnInfo
    val method6: String = "", //수단6
    @ColumnInfo
    val startPoint6: String = "", //출발지6
    @ColumnInfo
    val endPoint6: String = "", //도착지6
    @ColumnInfo
    val travelTime6: String = "", //걸리는 시간6
    @ColumnInfo
    val waitingTime: Int = -1, //기다리는 시간
    @ColumnInfo
    val waitingBus: String = "", //기다리는 이유
    @ColumnInfo
    val emptyNum: Int = -1, //빈자리 수
    @ColumnInfo
    val waitingNum: Int = -1, //대기인원 수
    @ColumnInfo
    val reservedNum: Int = 0, //예약자 수
)