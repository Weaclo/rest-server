package com.weaclo.restserver.models

import java.time.LocalDate

data class Forecast(
        val date: LocalDate,
        val temp: Double,
        val humidity: Double,
        val windSpeed: Double,
        val rainVolume: Double?,
        val snowVolume: Double?
)