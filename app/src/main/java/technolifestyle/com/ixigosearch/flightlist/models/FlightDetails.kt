package technolifestyle.com.ixigosearch.flightlist.models

import com.google.gson.annotations.SerializedName

object FlightModel {

    data class FlightDetails(
        @SerializedName("appendix")
        val appendix: Appendix,
        @SerializedName("flights")
        val flightList: List<Flight>
    )

    data class Appendix(
        @SerializedName("airlines")
        val airlines: Airlines,
        @SerializedName("airports")
        val airports: Airports,
        @SerializedName("providers")
        val providers: Providers
    )

    data class Flight(
        @SerializedName("originCode")
        val originCode: String,
        @SerializedName("destinationCode")
        val destinationCode: String,
        @SerializedName("departureTime")
        val departureTime: Long,
        @SerializedName("arrivalTime")
        val arrivalTime: Long,
        @SerializedName("fares")
        val fareList: List<Fare>,
        @SerializedName("airlineCode")
        val airlineCode: String,
        @SerializedName("class")
        val `class`: String
    )

    data class Airlines(val code: String, val name: String)

    data class Airports(val code: String, val name: String)

    data class Providers(val id: String, val name: String)

    data class Fare(
        @SerializedName("providerId")
        val providerId: String,
        @SerializedName("fare")
        val fare: Int)
}