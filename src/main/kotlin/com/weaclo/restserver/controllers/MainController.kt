package com.weaclo.restserver.controllers

import com.weaclo.restserver.logic.WeatherHandler
import com.weaclo.restserver.models.City
import com.weaclo.restserver.models.Forecast
import com.weaclo.restserver.services.TimeZoneApi
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired lateinit var weatherHandler: WeatherHandler
    @Autowired lateinit var timeZoneApi: TimeZoneApi


    @GetMapping("/")
    fun index(@RequestParam latitude: Double, @RequestParam longitude: Double): List<Forecast> {
        val city = City(null, null, latitude, longitude)
        return weatherHandler.getWeather(city)
    }


}