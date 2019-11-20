package technolifestyle.com.ixigosearch.flightlist.models

import technolifestyle.com.ixigosearch.flightlist.models.response.FlightModel

data class FlightDetails(
    val appendix: Appendix,
    val flightList: List<FlightModel.Flight>
)

data class Appendix(
    val airlines: List<Airline>,
    val airports: List<Airport>,
    val providers: List<Provider>
)

data class Airline(val code: String, val name: String)
data class Airport(val code: String, val name: String)
data class Provider(val id: String, val name: String)