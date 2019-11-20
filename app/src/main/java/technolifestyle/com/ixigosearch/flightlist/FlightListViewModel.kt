package technolifestyle.com.ixigosearch.flightlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technolifestyle.com.ixigosearch.flightlist.models.*
import technolifestyle.com.ixigosearch.flightlist.models.response.FlightModel
import technolifestyle.com.ixigosearch.utils.NetworkUtil
import timber.log.Timber

class FlightListViewModel(application: Application) : AndroidViewModel(application),
    Callback<FlightModel.FlightApiResponse> {

    var flightDetails: MutableLiveData<FlightDetails?> = MutableLiveData()

    fun fetchFlightDetails() {
        NetworkUtil.getApiImplementation(FlightApiInterface::class.java)
            .getFlightDetails()
            .enqueue(this)
    }

    override fun onFailure(call: Call<FlightModel.FlightApiResponse>, throwable: Throwable) {
        Timber.e("Error in API request: $throwable")
    }

    override fun onResponse(
        call: Call<FlightModel.FlightApiResponse>,
        response: Response<FlightModel.FlightApiResponse>
    ) {
        flightDetails.value = parseResponse(response)
    }

    private fun parseResponse(response: Response<FlightModel.FlightApiResponse>): FlightDetails? {
        var flightDetails: FlightDetails? = null
        response.body()?.let {
            val airlineList = it.appendix.asJsonObject.get("airlines").asJsonObject.entrySet()
                .map { (code, name) ->
                    Airline(code, name.asString)
                }
            val airportList = it.appendix.asJsonObject.get("airports").asJsonObject.entrySet()
                .map { (code, name) ->
                    Airport(code, name.asString)
                }
            val providerList = it.appendix.asJsonObject.get("providers").asJsonObject.entrySet()
                .map { (id, name) ->
                    Provider(id, name.asString)
                }
            val appendix = Appendix(airlineList, airportList, providerList)
            flightDetails = FlightDetails(appendix, it.flightList)
        }
        return flightDetails
    }
}