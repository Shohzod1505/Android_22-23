package ru.itis.kpfu.homework.screen;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.databinding.FragmentHelpBinding

class HelpFragment : Fragment(R.layout.fragment_help) {
    private var binding: FragmentHelpBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHelpBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}