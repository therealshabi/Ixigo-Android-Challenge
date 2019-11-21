package technolifestyle.com.ixigosearch.flightlist.api

import retrofit2.Call
import retrofit2.http.GET
import technolifestyle.com.ixigosearch.ApiConfig.API_VERSION
import technolifestyle.com.ixigosearch.ApiConfig.SAMPLE_FLIGHT_ENDPOINT
import technolifestyle.com.ixigosearch.flightlist.models.FlightModel

interface FlightApiInterface {

    @GET("/${API_VERSION}/${SAMPLE_FLIGHT_ENDPOINT}")
    fun getFlightDetails(): Call<FlightModel.FlightDetails>
}