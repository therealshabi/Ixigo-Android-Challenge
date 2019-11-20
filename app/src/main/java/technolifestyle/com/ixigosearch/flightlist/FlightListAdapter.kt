package technolifestyle.com.ixigosearch.flightlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.flight_item.view.*
import technolifestyle.com.ixigosearch.R
import technolifestyle.com.ixigosearch.flightlist.models.Flight

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

    override fun getItemCount(): Int {
        return flightList.size
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        holder.itemView.apply {
            flightClassTextView.text = flightList[position].`class`
            flightTimeTextView.text = holder.itemView.context.getString(
                R.string.time_text,
                flightList[position].departureTime,
                flightList[position].arrivalTime
            )
            flightNameTextView.text = flightList[position].airline
            airportInfoTextView.text = holder.itemView.context.getString(
                R.string.source_destination_text, flightList[position].originCode,
                flightList[position].destinationCode
            )
            flightDurationTextView.text = flightList[position].duration
            flightBestPriceTextView.text = holder.itemView.context.getString(
                R.string.best_price, flightList[position].bestPrice
            )
        }
    }

    inner class FlightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}