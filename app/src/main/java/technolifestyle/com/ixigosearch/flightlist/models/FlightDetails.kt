package technolifestyle.com.ixigosearch.flightlist.models

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import java.util.*

object FlightModel {

    data class FlightDetails(
        @SerializedName("appendix")
        val appendix: JsonElement? = null,
        @SerializedName("flights")
        val flightList: List<Flight> = ArrayList()
    )

    data class Appendix(
        val airlines: Airlines,
        val airports: Airports,
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