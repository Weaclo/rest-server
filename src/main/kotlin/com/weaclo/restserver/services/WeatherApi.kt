package com.weaclo.restserver.services

import com.weaclo.restserver.models.City
import com.weaclo.restserver.models.Forecast
import org.springframework.stereotype.Service

@Service
interface WeatherApi {
    fun getWeather(city: City): List<Forecast>
}