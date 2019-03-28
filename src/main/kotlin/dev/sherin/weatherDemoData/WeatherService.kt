package dev.sherin.weatherDemoData

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface WeatherService {
    fun getWeather(locationId: String): Weather
    fun getForeCastForDays(days: Int): Map<String, Weather>
}

@Service
class WeatherServiceImpl : WeatherService {

    @Autowired
    lateinit var weatherDao: WeatherDao

    @Autowired
    lateinit var locationService: LocationService

    override fun getWeather(locationId: String): Weather {
        val weather = weatherDao.getWeather(locationId)
        val location = locationService.getLocation(locationId)
        weather.location = location
        return weather
    }


    override fun getForeCastForDays(days: Int): Map<String, Weather> {
        //TODO("add location details")
        return weatherDao.getForeCastForDays(days)
    }


}

