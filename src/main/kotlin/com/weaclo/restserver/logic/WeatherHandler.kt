package com.weaclo.restserver.logic

import com.weaclo.restserver.models.City
import com.weaclo.restserver.models.Forecast
import org.springframework.stereotype.Service

@Service
interface WeatherHandler {
    fun getWeather(city: City): List<Forecast>
}