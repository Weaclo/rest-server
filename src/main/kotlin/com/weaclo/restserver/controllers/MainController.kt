package com.weaclo.restserver.controllers

import com.weaclo.restserver.models.City
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    @RequestMapping("/")
    fun index() = City(1, "SPB")
}