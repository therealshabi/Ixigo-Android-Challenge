package technolifestyle.com.ixigosearch.flightlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_flight_list.*
import technolifestyle.com.ixigosearch.R
import technolifestyle.com.ixigosearch.utils.HelperUtil
import timber.log.Timber

class FlightListActivity : AppCompatActivity() {

    private lateinit var flightListViewModel: FlightListViewModel
    private lateinit var flightListAdapter: FlightListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_list)

        flightListViewModel = ViewModelProviders.of(this).get(
            FlightListViewModel::class.java
        )

        flightListViewModel.fetchFlightDetails()

        setupFlightRecyclerView()

        setupBottomNavigation()

        flightListViewModel.run {
            flightDetails.observe(this@FlightListActivity, Observer {
                flightListAdapter.addAll(it?.flightList)
            })
            progressBarVisibility.observe(this@FlightListActivity, Observer { visible ->
                progressBar.visibility = if (visible) {
                    View.VISIBLE
                } else View.GONE
            })
        }
    }

    private fun setupBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.sortItem -> SortBottomSheetFragment().show(supportFragmentManager, "Sort")
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun setupFlightRecyclerView() {
        flightListAdapter = FlightListAdapter()
        flightListRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FlightListActivity)
            adapter = flightListAdapter
        }
    }

    fun sortFlightDetails(sortType: HelperUtil.SortType) {
        when(sortType) {
            HelperUtil.SortType.CHEAPEST -> Timber.d("Cheapest")
            HelperUtil.SortType.FASTEST -> Timber.d("Fastest")
            HelperUtil.SortType.EARLIEST -> Timber.d("Earliest")
        }
    }
}
