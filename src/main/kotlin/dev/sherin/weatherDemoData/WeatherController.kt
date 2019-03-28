package dev.sherin.weatherDemoData

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class WeatherController {

    @Autowired
    lateinit var weatherService: WeatherService

    @GetMapping("/weather/{locationId}")
    fun getWeather(@PathVariable locationId: String) = weatherService.getWeather(locationId)
}