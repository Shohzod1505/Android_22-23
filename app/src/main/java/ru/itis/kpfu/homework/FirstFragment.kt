package ru.itis.kpfu.homework;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import ru.itis.kpfu.homework.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first) {
    private var binding: FragmentFirstBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)

        var counter = 0
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
                    .replace(R.id.fragment_container, SecondFragment.newInstance(counter))
                    .addToBackStack("FirstFragment")
                    .commit()
            }

            btCounter.setOnClickListener {
                counter++
                binding?.tvCounterValue?.text = "Counter value: $counter"
            }
            btDialog.setOnClickListener {
                CounterDialog.newInstance(counter, fragmentManager = parentFragmentManager)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}