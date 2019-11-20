package technolifestyle.com.ixigosearch.flightlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technolifestyle.com.ixigosearch.flightlist.models.FlightModel
import technolifestyle.com.ixigosearch.flightlist.models.response.FlightModel
import technolifestyle.com.ixigosearch.utils.NetworkUtil
import timber.log.Timber

class FlightListViewModel(application: Application) : AndroidViewModel(application),
    Callback<FlightModel.FlightApiResponse> {

    fun getFlightDetails() {
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
        Timber.d("Response: ${response.body()}")
    }
}