package ru.itis.kpfu.homework

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.textfield.TextInputLayout
import ru.itis.kpfu.homework.databinding.DialogCounterBinding

class CounterDialog : DialogFragment() {
    private var binding: DialogCounterBinding? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val viewDialogCounter = binding?.tiDialogCounter
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_counter, null, false)
        val currentValue = arguments?.getInt(ARG_VALUE_DIALOG)

        return showDialog(
            title = "Operation with counter",
            view = dialogView,
            positiveButtonName = "Increase",
            negativeButtonName = "Cancel",
            neutralButtonName = "Decrease",
            positiveAction = {
                if(checkField(currentValue, viewDialogCounter, 0))
                    change(currentValue, viewDialogCounter, true)
            },
            negativeAction = {},
            neutralAction = {
                if(checkField(currentValue, viewDialogCounter, 1))
                    change(currentValue, viewDialogCounter, false)
            })
    }

    private fun change(currentValue: Int?, view: TextInputLayout?, action: Boolean) {
        val dialogValue = view?.editText?.text.toString().toInt()
        val newValue = if (action) {
            currentValue?.plus(dialogValue)
        } else {
            currentValue?.minus(dialogValue)
        }
        newValue?.let { arguments?.putInt(ARG_VALUE_DIALOG, it) }
    }

    private fun checkField(currentValue: Int?, view: TextInputLayout?, action: Int) : Boolean {
        val dialogValue = view?.editText?.text.toString().toInt()
        return when {
            dialogValue.toString().isEmpty() -> {
                view?.error = getString(R.string.error_empty)
                false
            }
            dialogValue !in 0..100 -> {
                view?.error = getString(R.string.error_data)
                false
            }
            (currentValue?.minus(dialogValue)!! < 0 && action == 1) -> {
                view?.error = getString(R.string.error_negative)
                false
            }
            else -> {
                view?.error = null
                true
            }
        }
    }

    companion object {
        private const val ARG_VALUE_DIALOG = "ARG_VALUE_DIALOG"
        fun newInstance(value: Int?, fragmentManager: FragmentManager) {
            CounterDialog().apply {
                arguments = Bundle().apply {
                    value?.let { putInt(ARG_VALUE_DIALOG, it) }
                }
            }
            return CounterDialog().show(fragmentManager, "Tag")
        }
    }

}