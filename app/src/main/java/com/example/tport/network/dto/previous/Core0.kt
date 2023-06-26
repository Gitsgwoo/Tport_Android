package com.example.tport.network.dto.previous

data class TravelDTO(
    val origin: String,
    val destination: String,
    val mode: String,
    val travelTime: String,
    val waitingTime: WaitingDTO?,
)

data class WaitingDTO(
    val emptyNum: Int,
    val waitingNum: Int,
    val arrivalTime: Int,
    val interval: Int,
)

data class MethodDTO(
    val id: Int,
    val methodName: String,
    val startPoint: String,
    val endPoint: String,
    val travelTime: String,
    var waitingTime: Int = -1,
    var emptyNum: Int = -1,
    var waitingNum: Int = -1,
    var reservedNum: Int = 0
)