package data

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city:String,
        @Query("appid") apiKey : String="34e8e88f9a1395a7eef0576361c23aa0",
        @Query("units") units: String="metric",

        @Query("lang") lang : String="fr"

    ) : WeatherData
}