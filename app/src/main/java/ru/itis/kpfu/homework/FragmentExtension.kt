package ru.itis.kpfu.homework

import android.app.AlertDialog
import android.app.Dialog
import android.text.Layout
import android.view.View
import androidx.fragment.app.Fragment

typealias Click = () -> Unit

fun Fragment.showDialog(
    title: String = "",
    view: View? = null,
    positiveButtonName: String = "",
    negativeButtonName: String = "",
    neutralButtonName: String = "",
    positiveAction: Click? = null,
    negativeAction: Click? = null,
    neutralAction: Click? = null
): Dialog =
    AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setView(view)
        .setPositiveButton(positiveButtonName) { dialog, _ ->
            positiveAction?.invoke()
            dialog.dismiss()
        }
        .setNegativeButton(negativeButtonName) { dialog, _ ->
            negativeAction?.invoke()
            dialog.dismiss()
        }
        .setNeutralButton(neutralButtonName) { dialog, _ ->
            neutralAction?.invoke()
            dialog.dismiss()
        }
        .show()
