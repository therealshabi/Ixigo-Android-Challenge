package technolifestyle.com.ixigosearch.flightlist.adapters

import androidx.recyclerview.widget.DiffUtil
import technolifestyle.com.ixigosearch.flightlist.models.FlightModel

class FlightListDiffCallback(
    private val oldFlightList: List<FlightModel.Flight>,
    private val updatedFlightList: List<FlightModel.Flight>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFlightList[oldItemPosition].hashCode() ==
                updatedFlightList[newItemPosition].hashCode()
    }

    override fun getOldListSize(): Int {
        return oldFlightList.size
    }

    override fun getNewListSize(): Int {
        return updatedFlightList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFlight = oldFlightList[oldItemPosition]
        val newFlight = updatedFlightList[newItemPosition]
        return (oldFlight.airlineCode == newFlight.airlineCode &&
                oldFlight.arrivalTime == newFlight.arrivalTime &&
                oldFlight.`class` == newFlight.`class` &&
                oldFlight.departureTime == newFlight.departureTime &&
                oldFlight.destinationCode == newFlight.destinationCode &&
                oldFlight.fareList == newFlight.fareList &&
                oldFlight.originCode == newFlight.originCode)
    }

}