package technolifestyle.com.ixigosearch.flightlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_flight_list.*
import technolifestyle.com.ixigosearch.R

class FlightListActivity : AppCompatActivity() {

    private lateinit var flightListViewModel: FlightListViewModel
    private lateinit var flightListAdapter: FlightListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_list)

        flightListViewModel = ViewModelProviders.of(this).get(
            FlightListViewModel::class.java)

        flightListViewModel.fetchFlightDetails()

        setupFlightRecyclerView()

        flightListViewModel.flightDetails.observe(this, Observer {
            flightListAdapter.addAll(it?.flightList)
        })
    }

    private fun setupFlightRecyclerView() {
        flightListAdapter = FlightListAdapter()
        flightListRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FlightListActivity)
            adapter = flightListAdapter
        }
    }
}
