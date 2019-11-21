package technolifestyle.com.ixigosearch.flightlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technolifestyle.com.ixigosearch.flightlist.api.FlightApiInterface
import technolifestyle.com.ixigosearch.flightlist.models.FlightModel
import technolifestyle.com.ixigosearch.utils.HelperUtil
import technolifestyle.com.ixigosearch.utils.NetworkUtil
import timber.log.Timber

class FlightListViewModel(application: Application) : AndroidViewModel(application),
    Callback<FlightModel.FlightDetails> {

    var flightDetails: MutableLiveData<FlightModel.FlightDetails> = MutableLiveData()
    var progressBarVisibility: MutableLiveData<Boolean> = MutableLiveData(true)

    fun fetchFlightDetails() {
        progressBarVisibility.value = true
        NetworkUtil.getApiImplementation(FlightApiInterface::class.java)
            .getFlightDetails()
            .enqueue(this)
    }

    override fun onFailure(call: Call<FlightModel.FlightDetails>, throwable: Throwable) {
        progressBarVisibility.value = false
        Timber.e("Error in API request: $throwable")
    }

    override fun onResponse(
        call: Call<FlightModel.FlightDetails>,
        response: Response<FlightModel.FlightDetails>
    ) {
        progressBarVisibility.value = false
        flightDetails.value = response.body()
    }

    /**
     * Method to sort the Flight List according to the provider SortType
     * @param sortType contains the requested sortType
     */
    fun sortFlightDetails(sortType: HelperUtil.SortType) {
        val flightInfo = (flightDetails.value as FlightModel.FlightDetails)
        when (sortType) {
            HelperUtil.SortType.CHEAPEST -> {
                flightDetails.value =
                    FlightModel.FlightDetails(
                        flightInfo.appendix,
                        flightInfo.flightList.sortedBy {
                            it.getBestPrice()
                        })
            }
            HelperUtil.SortType.FASTEST -> {
                flightDetails.value =
                    FlightModel.FlightDetails(
                        flightInfo.appendix,
                        flightInfo.flightList.sortedBy {
                            it.getDuration()
                        })
            }
            HelperUtil.SortType.EARLIEST -> {
                flightDetails.value =
                    FlightModel.FlightDetails(
                        flightInfo.appendix,
                        flightInfo.flightList.sortedBy {
                            it.departureTime
                        })
            }
        }
    }
}