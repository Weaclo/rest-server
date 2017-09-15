package com.weaclo.restserver.services.impl

import com.weaclo.restserver.models.City
import com.weaclo.restserver.services.WeatherApi
import com.weaclo.restserver.utils.Constants
import khttp.get
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class WeatherApiImpl: WeatherApi {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun getWeather(city: City) {
        val httpResult = get(String.format(Constants.WeatherApi.URL, city.latitude, city.longitude))
        log.info(httpResult.text)
    }
}