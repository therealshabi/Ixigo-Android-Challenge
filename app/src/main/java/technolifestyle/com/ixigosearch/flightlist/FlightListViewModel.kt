package technolifestyle.com.ixigosearch.flightlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technolifestyle.com.ixigosearch.flightlist.models.Flight
import technolifestyle.com.ixigosearch.flightlist.models.FlightDetails
import technolifestyle.com.ixigosearch.flightlist.models.response.FlightModel
import technolifestyle.com.ixigosearch.utils.HelperUtil
import technolifestyle.com.ixigosearch.utils.HelperUtil.getDuration
import technolifestyle.com.ixigosearch.utils.HelperUtil.getTime
import technolifestyle.com.ixigosearch.utils.NetworkUtil
import timber.log.Timber

class FlightListViewModel(application: Application) : AndroidViewModel(application),
    Callback<FlightModel.FlightApiResponse> {

    var flightDetails: MutableLiveData<FlightDetails?> = MutableLiveData()
    var progressBarVisibility: MutableLiveData<Boolean> = MutableLiveData(true)

    fun fetchFlightDetails() {
        progressBarVisibility.value = true
        NetworkUtil.getApiImplementation(FlightApiInterface::class.java)
            .getFlightDetails()
            .enqueue(this)
    }

    override fun onFailure(call: Call<FlightModel.FlightApiResponse>, throwable: Throwable) {
        progressBarVisibility.value = false
        Timber.e("Error in API request: $throwable")
    }

    override fun onResponse(
        call: Call<FlightModel.FlightApiResponse>,
        response: Response<FlightModel.FlightApiResponse>
    ) {
        progressBarVisibility.value = false
        flightDetails.value = parseResponse(response)
    }

    private fun parseResponse(response: Response<FlightModel.FlightApiResponse>): FlightDetails? {
        var flightDetails: FlightDetails? = null
        response.body()?.let {
            val flightData = getFlightData(it.flightInfo, it.appendix)
            flightDetails = FlightDetails(it.appendix, flightData)
        }
        return flightDetails
    }

    private fun getFlightData(
        flightInfo: List<FlightModel.FlightInfo>, appendix: FlightModel.Appendix
    ): List<Flight> {
        val flightList: ArrayList<Flight> = ArrayList()
        flightInfo.forEach {
            val fareMap: HashMap<String, Int> = HashMap()
            var bestPrice: Int = Integer.MAX_VALUE
            it.fareList.forEach { fare ->
                fareMap[appendix.providers[fare.providerId]!!] = fare.price
                if (fare.price < bestPrice) {
                    bestPrice = fare.price
                }
            }
            flightList.add(
                Flight(
                    it.originCode,
                    it.destinationCode,
                    getTime(it.departureTime),
                    getTime(it.arrivalTime),
                    getDuration(it.departureTime, it.arrivalTime),
                    fareMap,
                    appendix.airlines[it.airlineCode]!!,
                    it.`class`,
                    bestPrice
                )
            )
        }
        return flightList
    }

    fun sortFlightDetails(sortType: HelperUtil.SortType) {
        when (sortType) {
            HelperUtil.SortType.CHEAPEST -> {
                flightDetails.value =
                    FlightDetails((flightDetails.value as FlightDetails).appendix,
                        (flightDetails.value as FlightDetails).flightList.sortedBy {
                            it.bestPrice
                        })
            }
            HelperUtil.SortType.FASTEST -> {

            }
            HelperUtil.SortType.EARLIEST -> {

            }
        }
    }
}