package dev.sherin.weatherDemoData

import org.springframework.stereotype.Repository

data class Location(val id: String,
                    val name: String?
)

data class Weather(var location: Location = Location("NoLocation", "NoLocation"),
                   val temperature: String,
                   val precipitation: String,
                   val wind: String)

interface WeatherDao {
    fun getWeather(locationId: String): Weather
    fun getForeCastForDays(days: Int): Map<String, Weather>
}

@Repository
class WeatherDaoImpl : WeatherDao {
    override fun getWeather(locationId: String): Weather {
        return Weather(temperature = "18 degree Celcius", precipitation = "80%", wind = "15kmpl")
    }

    override fun getForeCastForDays(days: Int): Map<String, Weather> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}