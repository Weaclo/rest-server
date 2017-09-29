package com.weaclo.restserver.models

import java.time.LocalDateTime

data class Forecast(
        val date: LocalDateTime,
        val temp: Double,
        val humidity: Double?,
        val windSpeed: Double?,
        val rainVolume: Double?,
        val snowVolume: Double?
)