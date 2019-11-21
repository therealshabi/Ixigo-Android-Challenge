package technolifestyle.com.ixigosearch.flightlist.adapters

import androidx.recyclerview.widget.DiffUtil
import technolifestyle.com.ixigosearch.flightlist.models.FlightModel

/**
 * This class is a helper class to DiffUtil providing it required information regarding the
 * old and new data list, the DiffUtil then calculate the diff using Eugene Myers algorithm using the provided data in this class
 */
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