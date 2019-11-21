package technolifestyle.com.ixigosearch.flightlist.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import technolifestyle.com.ixigosearch.utils.HelperUtil

object FlightModel {

    data class FlightDetails(
        @SerializedName("appendix")
        val appendix: Appendix,
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
        val `class`: String,
        @Expose(serialize = false, deserialize = false)
        private var bestPrice: Int
    ) {

        fun getFormattedDuration(): String {
            return HelperUtil.getFormattedDuration(departureTime, arrivalTime)
        }

        fun getDuration(): Long {
            return HelperUtil.getDuration(departureTime, arrivalTime)
        }

        fun getDepartureTime(): String {
            return HelperUtil.getTime(departureTime)
        }

        fun getArrivalTime(): String {
            return HelperUtil.getTime(arrivalTime)
        }

        fun getBestPrice(): Int {
            if (this.bestPrice != 0) {
                return this.bestPrice
            }
            this.bestPrice = Int.MAX_VALUE
            fareList.forEach { fare ->
                if (fare.price < bestPrice) {
                    bestPrice = fare.price
                }
            }
            return bestPrice
        }
    }

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