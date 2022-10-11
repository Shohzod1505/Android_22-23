package ru.itis.kpfu.homework;

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.itis.kpfu.homework.databinding.FragmentSecondBinding
import ru.itis.kpfu.homework.Constants.APP_PREFERENCES
import ru.itis.kpfu.homework.Constants.COUNTER_VALUE

class SecondFragment : Fragment(R.layout.fragment_second) {
    private var binding: FragmentSecondBinding? = null
    private lateinit var preferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondBinding.bind(view)

        preferences = requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        val counter = preferences.getInt(COUNTER_VALUE, 0)
        binding?.tvValue?.text = "Counter value: $counter"

        when(counter) {
            in 0..50 -> binding?.root?.setBackgroundColor(Color.RED)
            in 51..100 -> binding?.root?.setBackgroundColor(Color.GREEN)
            else -> {
                binding?.root?.setBackgroundColor(Color.BLUE)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}