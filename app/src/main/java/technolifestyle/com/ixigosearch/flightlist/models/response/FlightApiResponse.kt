package technolifestyle.com.ixigosearch.flightlist.models.response

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

object FlightModel {

    data class FlightApiResponse(
        @SerializedName("appendix")
        val appendix: JsonElement,
        @SerializedName("flights")
        val flightList: List<Flight>
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

    data class Fare(
        @SerializedName("providerId")
        val providerId: String,
        @SerializedName("fare")
        val fare: Int)
}