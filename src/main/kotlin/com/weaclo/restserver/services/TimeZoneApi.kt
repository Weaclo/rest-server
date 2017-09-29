package com.weaclo.restserver.services

import com.weaclo.restserver.models.City
import org.springframework.stereotype.Service
import java.time.ZoneId

@Service
interface TimeZoneApi {
    fun getTimeZone(city: City, timestamp: Long): ZoneId
}