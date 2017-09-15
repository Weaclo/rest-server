package com.weaclo.restserver.controllers

import com.weaclo.restserver.logic.WeatherHandler
import com.weaclo.restserver.models.City
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired lateinit var weatherHandler: WeatherHandler

    @RequestMapping("/")
    fun index() {
        weatherHandler.getWeather(City(1, "SPB", 59.9, 30.2))
    }


}