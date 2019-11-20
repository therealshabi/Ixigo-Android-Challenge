package technolifestyle.com.ixigosearch.flightlist.models.response

import com.google.gson.annotations.SerializedName

object FlightModel {

    data class FlightApiResponse(
        @SerializedName("appendix")
        val appendix: Appendix,
        @SerializedName("flights")
        val flightInfo: List<FlightInfo>
    )

    data class FlightInfo(
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

    data class Appendix(
        @SerializedName("airlines")
        val airlines: HashMap<String, String>,
        @SerializedName("airports")
        val airports: HashMap<String, String>,
        @SerializedName("providers")
        val providers: HashMap<String, String>
    )

    data class Fare(
        @SerializedName("providerId")
        val providerId: String,
        @SerializedName("fare")
        val price: Int)
}