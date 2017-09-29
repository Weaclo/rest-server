package com.weaclo.restserver.services.impl

import com.weaclo.restserver.models.City
import com.weaclo.restserver.models.Forecast
import com.weaclo.restserver.services.TimeZoneApi
import com.weaclo.restserver.services.WeatherApi
import com.weaclo.restserver.utils.Constants
import khttp.get
import khttp.responses.Response
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Component
class WeatherApiImpl : WeatherApi {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired lateinit var timeZoneApi: TimeZoneApi

    override fun getWeather(city: City): List<Forecast> {
        val params = mapOf(
                "APPID" to Constants.WeatherApi.Key,
                "units" to "metric",
                "lat" to city.latitude.toString(),
                "lon" to city.longitude.toString()
        )
        val httpResult: Response = get(Constants.WeatherApi.URL, params = params)
        log.info(httpResult.jsonObject.toString())
        return parseWeather(city, httpResult.jsonObject)
    }

    private fun parseWeather(city: City, json: JSONObject): List<Forecast> {

        val zoneId: ZoneId = timeZoneApi.getTimeZone(city, System.currentTimeMillis() / 1000)
        log.info("ZoneId: ${zoneId.id}")

        val listForecast = mutableListOf<Forecast>()

        val jsonArray: JSONArray = json.getJSONArray("list")
        (0..(jsonArray.length() - 1))
                .map { jsonArray.getJSONObject(it) }
                .mapTo(listForecast) {
                    Forecast(
                            getDateTime(it.getLong("dt"), zoneId),
                            it.getJSONObject("main").getDouble("temp"),
                            it.getJSONObjectOrNull("main")?.getDoubleOrNull("humidity"),
                            it.getJSONObjectOrNull("wind")?.getDoubleOrNull("speed"),
                            it.getJSONObjectOrNull("rain")?.getDoubleOrNull("3h"),
                            it.getJSONObjectOrNull("snow")?.getDoubleOrNull("3h")
                    )
                }

        print(listForecast)
        return listForecast
    }

    private fun getDateTime(timestamp: Long, zoneId: ZoneId) =
            LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), zoneId)

    private fun JSONObject.getJSONObjectOrNull(key: String): JSONObject? = try {
        this.getJSONObject(key)
    } catch (e: JSONException) {
        null
    }

    private fun JSONObject.getDoubleOrNull(key: String): Double? = try {
        this.getDouble(key)
    } catch (e: JSONException) {
        null
    }
}