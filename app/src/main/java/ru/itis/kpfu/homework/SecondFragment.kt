package ru.itis.kpfu.homework;

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import ru.itis.kpfu.homework.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {
    private var binding: FragmentSecondBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondBinding.bind(view)

        val counter = arguments?.getInt(ARG_VALUE)
        Log.d("Debug", counter.toString())
        binding?.tvValue?.text = "Counter value: $counter"

        when(counter) {
            in 0..50 -> binding?.root?.setBackgroundColor(Color.RED)
            in 51..100 -> binding?.root?.setBackgroundColor(Color.GREEN)
            else -> {
                binding?.root?.setBackgroundColor(Color.BLUE)
            }
        }

    }

    companion object {
        private const val ARG_VALUE ="ARG_VALUE"
        fun newInstance(value: Int?) = SecondFragment().apply {
            arguments = Bundle().apply {
                value?.let { putInt(ARG_VALUE, it) }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}