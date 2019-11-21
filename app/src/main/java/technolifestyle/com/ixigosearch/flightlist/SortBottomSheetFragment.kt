package technolifestyle.com.ixigosearch.flightlist

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_sort_bottom_sheet.view.*
import technolifestyle.com.ixigosearch.R
import technolifestyle.com.ixigosearch.utils.HelperUtil

class SortBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var dialogView: View
    private lateinit var flightListViewModel: FlightListViewModel

    @SuppressLint("InflateParams")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        dialogView = LayoutInflater.from(context)
            .inflate(R.layout.fragment_sort_bottom_sheet, null)
        dialog.setContentView(dialogView)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            flightListViewModel = ViewModelProviders.of(it).get(FlightListViewModel::class.java)
        }
        dialogView.apply {
            cheapestSortButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    dismiss()
                    flightListViewModel.sortFlightDetails(HelperUtil.SortType.CHEAPEST)
                }
            }
            earliestSortButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    dismiss()
                    flightListViewModel.sortFlightDetails(HelperUtil.SortType.EARLIEST)
                }
            }
            fastestSortButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    dismiss()
                    flightListViewModel.sortFlightDetails(HelperUtil.SortType.FASTEST)
                }
            }
        }
    }
}
