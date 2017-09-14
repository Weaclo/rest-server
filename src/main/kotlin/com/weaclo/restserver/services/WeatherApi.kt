package com.weaclo.restserver.services

import com.weaclo.restserver.models.City
import org.springframework.stereotype.Service

@Service
interface WeatherApi {
    fun getWeather(city: City)
}