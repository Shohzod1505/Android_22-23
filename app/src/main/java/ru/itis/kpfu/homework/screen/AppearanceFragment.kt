package ru.itis.kpfu.homework.screen;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.databinding.FragmentAppearanceBinding

class AppearanceFragment : Fragment(R.layout.fragment_appearance) {
    private var binding: FragmentAppearanceBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAppearanceBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}