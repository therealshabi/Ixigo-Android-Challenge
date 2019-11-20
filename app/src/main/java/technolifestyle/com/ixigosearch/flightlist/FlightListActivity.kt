package technolifestyle.com.ixigosearch.flightlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import technolifestyle.com.ixigosearch.R
import timber.log.Timber

class FlightListActivity : AppCompatActivity() {

    private lateinit var flightListViewModel: FlightListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_list)

        flightListViewModel = ViewModelProviders.of(this).get(
            FlightListViewModel::class.java)

        flightListViewModel.fetchFlightDetails()

        flightListViewModel.flightDetails.observe(this, Observer {
            Timber.d("LiveData: $it")
        })
    }
}
