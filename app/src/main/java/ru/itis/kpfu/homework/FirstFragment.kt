package ru.itis.kpfu.homework;

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import ru.itis.kpfu.homework.Constants.APP_PREFERENCES
import ru.itis.kpfu.homework.Constants.COUNTER_VALUE
import ru.itis.kpfu.homework.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first) {
    private var binding: FragmentFirstBinding? = null
    private lateinit var preferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)

        preferences = requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        var counter = preferences.getInt(COUNTER_VALUE, 0)
        binding?.tvCounterValue?.text = "Counter value: $counter"

        binding?.run {
            btNext.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    )
                    .replace(R.id.fragment_container, SecondFragment())
                    .addToBackStack("FirstFragment")
                    .commit()
            }
            btCounter.setOnClickListener {
                preferences.edit {
                    counter++
                    putInt(COUNTER_VALUE, counter)
                    commit()
                    binding?.tvCounterValue?.text = "Counter value: $counter"
                }
            }
            btDialog.setOnClickListener {
                CounterDialog.newInstance(fragmentManager = parentFragmentManager)
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}