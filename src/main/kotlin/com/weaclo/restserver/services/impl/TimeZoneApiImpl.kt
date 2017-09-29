package com.weaclo.restserver.services.impl

import com.weaclo.restserver.models.City
import com.weaclo.restserver.services.TimeZoneApi
import com.weaclo.restserver.utils.Constants
import khttp.get
import khttp.responses.Response
import org.json.JSONException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.ZoneId


/**
 * Created on : 29.09.2017
 * Author     : aliv0816
 */

@Component
class TimeZoneApiImpl : TimeZoneApi {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun getTimeZone(city: City, timestamp: Long): ZoneId {
        val params = mapOf(
                "key" to Constants.TimeZoneApi.Key,
                "location" to "${city.latitude},${city.longitude}",
                "timestamp" to timestamp.toString()
        )
        val httpResult: Response = get(Constants.TimeZoneApi.URL, params = params)
        val timeZoneId: String

        timeZoneId = try {
            httpResult.jsonObject.getString("timeZoneId")
        } catch (e: JSONException) {
            log.error("Could not get TimeZone")
            ZoneId.systemDefault().id
        }

        return ZoneId.of(timeZoneId)
    }
}