package technolifestyle.com.ixigosearch.flightlist.models

import technolifestyle.com.ixigosearch.flightlist.models.response.FlightModel

data class FlightDetails(
    val appendix: FlightModel.Appendix,
    val flightList: List<Flight>
)

data class Flight(
    val originCode: String,
    val destinationCode: String,
    val departureTime: String,
    val arrivalTime: String,
    val duration: String,
    val fares: Map<String, Int>,
    val airline: String,
    val `class`: String,
    val bestPrice: Int
)