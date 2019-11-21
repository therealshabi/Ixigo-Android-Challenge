package technolifestyle.com.ixigosearch.flightlist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.flight_item.view.*
import kotlinx.android.synthetic.main.provider_item.view.*
import technolifestyle.com.ixigosearch.R
import technolifestyle.com.ixigosearch.animation.Animations
import technolifestyle.com.ixigosearch.flightlist.models.FlightModel
import timber.log.Timber

class FlightListAdapter : RecyclerView.Adapter<FlightListAdapter.FlightViewHolder>() {

    private lateinit var appendix: FlightModel.Appendix
    private val flightList: ArrayList<FlightModel.Flight> = ArrayList()

    fun add(flightInfo: FlightModel.FlightDetails) {
        this.appendix = flightInfo.appendix
        val diffResult =
            DiffUtil.calculateDiff(FlightListDiffCallback(this.flightList, flightInfo.flightList))
        flightList.clear()
        flightList.addAll(flightInfo.flightList)
        diffResult.dispatchUpdatesTo(this)
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
                    getDepartureTime(),
                    getArrivalTime()
                )
                flightNameTextView.text = appendix.airlines[airlineCode]
                airportInfoTextView.text = holder.itemView.context.getString(
                    R.string.source_destination_text, originCode,
                    destinationCode
                )
                flightDurationTextView.text = getFormattedDuration()
                flightBestPriceTextView.text = holder.itemView.context.getString(
                    R.string.price, getBestPrice()
                )
                if (providerDetails.childCount > 0) {
                    return
                }
                val inflater = LayoutInflater.from(holder.itemView.context)
                fareList.forEach { (providerId, price) ->
                    Timber.d("ProviderId: $providerId, Price: $price")
                    val providerView =
                        inflater.inflate(R.layout.provider_item, providerDetails, false)
                    providerView.providerNameTextView.text =
                        appendix.providers[providerId]
                    providerView.providerFareTextView.text = holder.itemView.context.getString(
                        R.string.price, price
                    )
                    providerDetails.addView(providerView)
                }
            }
            viewFareContainer.setOnClickListener {
                Timber.d("onclick 1")
                if (providerDetails.visibility == View.GONE) {
                    viewFareTextView.text = holder.itemView.context.getString(R.string.hide_fares_from_other_providers)
                    expandArrow.animate().rotation(180f).start()
                    Animations.expand(providerDetails)
                } else {
                    viewFareTextView.text = holder.itemView.context.getString(R.string.view_fares_from_other_providers)
                    expandArrow.animate().rotation(0f).start()
                    Animations.collapse(providerDetails)
                }
            }
        }
    }

    inner class FlightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}