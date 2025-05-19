package data

data class WeatherData(
    val sys:Sys,
    val summary :String,
    val main: Main,
    val weather : List<Weather>,
    val wind: Wind

)

class Wind {
    val speed: Double = 0.0

}

class Weather {
    val description: String = ""

}

class Sys {
    val country : String = ""

}

data class Main(
    val temp: Double,
    val humidity:Int
)
