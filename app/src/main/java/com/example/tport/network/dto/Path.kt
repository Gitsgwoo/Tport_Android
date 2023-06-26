package com.example.tport.network.dto

data class SearchRequest(
    val originName: String,
    val destinationName: String,
    val departureTime: String,
)

data class SearchResponse(
    val id: Int,
    val getOnBusStop: String,
    val getOffBusStop: String,
    val bus: Bus

)