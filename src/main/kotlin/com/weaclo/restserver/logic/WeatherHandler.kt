package com.weaclo.restserver.logic

import com.weaclo.restserver.models.City
import org.springframework.stereotype.Service

@Service
interface WeatherHandler {
    fun getWeather(city: City)
}