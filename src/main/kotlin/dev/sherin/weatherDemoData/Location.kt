package dev.sherin.weatherDemoData

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient("weather-location")
interface LocationClient {

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = ["api/location/{locationId}"])
    fun getLocation(@PathVariable locationId: String): Location
}

interface LocationService {
    fun getLocation(locationId: String): Location
}

@Service
class LocationServiceImpl : LocationService {

    @Autowired
    lateinit var locationClient: LocationClient

    val logger = LoggerFactory.getLogger(LocationServiceImpl::class.java)

    @HystrixCommand(fallbackMethod = "getNullLocation",
            commandProperties = arrayOf(
                    HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
            )
    )
    override fun getLocation(locationId: String): Location {
        Thread.sleep(3000)
        return locationClient.getLocation(locationId)
    }

    fun getNullLocation(locationId: String): Location {
        logger.info("time out reached, returning Null location")
        return Location("Null", "Null")
    }
}