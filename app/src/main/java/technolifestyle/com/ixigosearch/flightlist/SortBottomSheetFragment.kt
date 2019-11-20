package technolifestyle.com.ixigosearch.flightlist

import android.annotation.SuppressLint
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import technolifestyle.com.ixigosearch.R

class SortBottomSheetFragment : BottomSheetDialogFragment() {

    @SuppressLint("InflateParams")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val view = LayoutInflater.from(context)
            .inflate(R.layout.fragment_sort_bottom_sheet, null)
        dialog.setContentView(view)

        val params: CoordinatorLayout.LayoutParams =
            (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior

        if (behavior != null && behavior is BottomSheetBehavior) {

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(view: View, newState: Int) {
                    val state = when (newState) {
                        BottomSheetBehavior.STATE_DRAGGING -> {
                            "DRAGGING"
                        }
                        BottomSheetBehavior.STATE_SETTLING -> {
                            "SETTLING"
                        }
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            "EXPANDED"
                        }
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            "COLLAPSED"
                        }
                        BottomSheetBehavior.STATE_HIDDEN -> {
                            "HIDDEN"
                        }
                        BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                            ""
                        }
                        else -> ""
                    }
                    Toast.makeText(
                        context,
                        "Bottom Sheet State Changed to: $state",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }
            })
        }
    }
}
