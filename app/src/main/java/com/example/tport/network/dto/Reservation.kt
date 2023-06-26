package com.example.tport.network.dto

data class ReservationRequest(
    val busId: Int,
    val getOnBusStop: String,
    val reservationRequestDatetime: String
)

data class ReservationResponse(
    val userId: Int,
    val busId: Int,
    val busNum: String,
    val busStopName: String,
    val seatNum: String,
    val time: String
)

