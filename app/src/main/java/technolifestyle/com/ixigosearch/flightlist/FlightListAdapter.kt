package technolifestyle.com.ixigosearch.flightlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.flight_item.view.*
import kotlinx.android.synthetic.main.provider_item.view.*
import technolifestyle.com.ixigosearch.R
import technolifestyle.com.ixigosearch.flightlist.models.Flight
import timber.log.Timber

class FlightListAdapter : RecyclerView.Adapter<FlightListAdapter.FlightViewHolder>() {

    private val flightList: ArrayList<Flight> = ArrayList()

    fun addAll(flights: List<Flight>?) {
        if (flights == null) {
            return
        }
        flightList.clear()
        flightList.addAll(flights)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        return FlightViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.flight_item,
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return flightList[position].hashCode()
    }

    override fun getItemCount(): Int {
        return flightList.size
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        holder.itemView.apply {
            flightList[position].apply {
                flightClassTextView.text = `class`
                flightTimeTextView.text = holder.itemView.context.getString(
                    R.string.time_text,
                    departureTime,
                    arrivalTime
                )
                flightNameTextView.text = airline
                airportInfoTextView.text = holder.itemView.context.getString(
                    R.string.source_destination_text, originCode,
                    destinationCode
                )
                flightDurationTextView.text = duration
                flightBestPriceTextView.text = holder.itemView.context.getString(
                    R.string.price, bestPrice
                )
                if (providerDetails.childCount > 0) {
                    return
                }
                val inflater = LayoutInflater.from(holder.itemView.context)
                fares.entries.forEach { (providerName, fare) ->
                    val providerView =
                        inflater.inflate(R.layout.provider_item, providerDetails, false)
                    providerView.providerNameTextView.text = providerName
                    providerView.providerFareTextView.text = holder.itemView.context.getString(
                        R.string.price, fare
                    )
                    providerDetails.addView(providerView)
                }
            }
            mainContainer.setOnClickListener {
                Timber.d("onclick")
                if (providerDetails.visibility == View.GONE) {
                    Timber.d("Visible")
                    providerDetails.visibility = View.VISIBLE
                    mainContentDivider.visibility = View.INVISIBLE
                    divider.visibility = View.VISIBLE
                } else {
                    Timber.d("InVisible")
                    providerDetails.visibility = View.GONE
                    mainContentDivider.visibility = View.VISIBLE
                    divider.visibility = View.GONE
                }
            }
        }
    }

    inner class FlightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}