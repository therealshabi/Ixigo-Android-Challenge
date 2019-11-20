package technolifestyle.com.ixigosearch.flightlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technolifestyle.com.ixigosearch.flightlist.models.FlightModel
import technolifestyle.com.ixigosearch.utils.NetworkUtil
import timber.log.Timber

class FlightListViewModel(application: Application) : AndroidViewModel(application),
    Callback<FlightModel.FlightDetails> {

    fun getFlightDetails() {
        NetworkUtil.getApiImplementation(FlightApiInterface::class.java)
            .getFlightDetails()
            .enqueue(this)
    }

    override fun onFailure(call: Call<FlightModel.FlightDetails>, throwable: Throwable) {
        Timber.e("Error in API request: $throwable")
    }

    override fun onResponse(
        call: Call<FlightModel.FlightDetails>,
        response: Response<FlightModel.FlightDetails>
    ) {
        Timber.d("Response: ${response.body()}")
    }
}