package ru.itis.kpfu.homework

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.core.content.edit
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.textfield.TextInputLayout
import ru.itis.kpfu.homework.Constants.COUNTER_VALUE

class CounterDialog : DialogFragment() {
    private lateinit var preferences: SharedPreferences

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        preferences = requireActivity().getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        val viewDialog = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_counter, null, false)
        val viewFirstFragment = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_first, null, false)
        val viewDialogCounter = viewDialog.findViewById<TextInputLayout>(R.id.ti_dialog_counter)
        val viewCounter = viewFirstFragment.findViewById<TextView>(R.id.tv_counter_value)

        val dialogCounter = AlertDialog.Builder(requireContext())
            .setTitle("Operation with counter")
            .setView(viewDialog)
            .setPositiveButton("Increase") { _, _ ->
            }
            .setNegativeButton("Cancel") { _, _ ->
            }
            .setNeutralButton("Decrease") { _, _ ->
            }
            .show()

        dialogCounter.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            if(checkField(viewDialogCounter, 0)) {
                val newValue = change(viewDialogCounter, true)
                viewCounter.text = "Counter value: $newValue"
                dialogCounter.dismiss()
            }
        }
        dialogCounter.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener {
            dialogCounter.dismiss()
        }
        dialogCounter.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener {
            if(checkField(viewDialogCounter, 1)) {
                val newValue = change(viewDialogCounter, false)
                viewCounter.text = "Counter value: $newValue"
                dialogCounter.dismiss()
            }
        }

        return dialogCounter

    }

    private fun change(view: TextInputLayout, action: Boolean) : Int {
        val currentValue = preferences.getInt(COUNTER_VALUE, 0)
        val dialogValue = view.editText?.text.toString().toInt()
        val newValue = if (action) {
            currentValue + dialogValue
        } else {
            currentValue - dialogValue
        }
        preferences.edit {
            putInt(COUNTER_VALUE, newValue)
            commit()
        }
        return newValue
    }

    private fun checkField(view: TextInputLayout, action: Int) : Boolean {
        val currentValue = preferences.getInt(COUNTER_VALUE, 0)
        val dialogValue = view.editText?.text.toString()
        return when {
            dialogValue.isEmpty() -> {
                view.error = getString(R.string.error_empty)
                false
            }
            dialogValue.toInt() !in 0..100 -> {
                view.error = getString(R.string.error_data)
                false
            }
            (currentValue - dialogValue.toInt() < 0 && action == 1) -> {
                view.error = getString(R.string.error_negative)
                false
            }
            else -> {
                view.error = null
                true
            }
        }
    }

    companion object {
        fun newInstance(fragmentManager: FragmentManager) {
            return CounterDialog().show(fragmentManager, "Tag")
        }
    }

}