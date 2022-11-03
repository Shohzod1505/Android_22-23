package ru.itis.kpfu.homework.screen;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.databinding.FragmentAccountBinding

class AccountFragment : Fragment(R.layout.fragment_account) {
    private var binding: FragmentAccountBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}