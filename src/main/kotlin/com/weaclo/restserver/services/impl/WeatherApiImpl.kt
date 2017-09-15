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
        val params = mapOf(
                "APPID" to Constants.WeatherApi.Key,
                "metric" to "unit",
                "lat" to city.latitude.toString(),
                "lon" to city.longitude.toString()
                )
        val httpResult = get(Constants.WeatherApi.URL, params = params)
        log.info(httpResult.jsonObject.toString())
    }
}