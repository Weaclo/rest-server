package com.weaclo.restserver.utils

object Constants {
    object WeatherApi {
        private val Key = "c55ad3bd5cefe6f72292dddaadcab7ba"
        val URL = "http://api.openweathermap.org/data/2.5/forecast?APPID=$Key&units=metric&lat=%s&lon=%s"
    }
}