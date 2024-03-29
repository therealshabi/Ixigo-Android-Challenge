package technolifestyle.com.ixigosearch.flightlist.views

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_flight_list.*
import technolifestyle.com.ixigosearch.R
import technolifestyle.com.ixigosearch.flightlist.FlightListViewModel
import technolifestyle.com.ixigosearch.flightlist.adapters.FlightListAdapter


class FlightListActivity : AppCompatActivity() {

    private lateinit var flightListViewModel: FlightListViewModel
    private lateinit var flightListAdapter: FlightListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_list)

        setWindowStatusBarColor()

        // Obtaining the FlightListViewModel instance
        flightListViewModel = ViewModelProviders.of(this).get(
            FlightListViewModel::class.java
        )

        setupFlightRecyclerView()

        setupBottomNavigation()

        flightListViewModel.run {
            // Observes on Live data of flightDetails present in FlightListViewModel and update accordingly
            flightDetails.observe(this@FlightListActivity, Observer {
                bottomNavigation.visibility = View.VISIBLE
                flightListAdapter.add(it)
                flightListRecyclerView.smoothScrollToPosition(0)
            })

            /* Observes on Live data of progressBarVisibility present in FlightListViewModel
             set the progress bar visibility accordingly
             */
            progressBarVisibility.observe(this@FlightListActivity, Observer { visible ->
                progressBar.visibility = if (visible) {
                    View.VISIBLE
                } else View.GONE
            })
        }
    }

    /**
     * Method to set status bar background gradient
     */
    private fun setWindowStatusBarColor() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        window.setBackgroundDrawable(getDrawable(R.drawable.toolbar_background))
    }

    /**
     * Method to setup bottom navigation bar and its corresponding action
     */
    private fun setupBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.sortItem -> SortBottomSheetFragment().show(supportFragmentManager, "Sort")
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    /**
     * Method to setup FlightRecyclerView along with its layout manager and adapter
     */
    private fun setupFlightRecyclerView() {
        flightListAdapter =
            FlightListAdapter()
        flightListRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FlightListActivity)
            adapter = flightListAdapter
        }
    }
}
