package technolifestyle.com.ixigosearch.flightlist

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import technolifestyle.com.ixigosearch.R

class SortBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var activity: FlightListActivity

    @SuppressLint("InflateParams")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val view = LayoutInflater.from(context)
            .inflate(R.layout.fragment_sort_bottom_sheet, null)
        dialog.setContentView(view)
    }

    override fun onAttach(context: Context) {
        if (context is FlightListActivity) {
            activity = FlightListActivity()
        }
        super.onAttach(context)
    }
}
