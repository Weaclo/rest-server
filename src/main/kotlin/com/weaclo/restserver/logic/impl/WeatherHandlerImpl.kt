package com.weaclo.restserver.logic.impl

import com.weaclo.restserver.logic.WeatherHandler
import com.weaclo.restserver.models.City
import com.weaclo.restserver.services.WeatherApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WeatherHandlerImpl: WeatherHandler {

    @Autowired private lateinit var weatherApi: WeatherApi

    override fun getWeather(city: City) {
        weatherApi.getWeather(city)
    }
}