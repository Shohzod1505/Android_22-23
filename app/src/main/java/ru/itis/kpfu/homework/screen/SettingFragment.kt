package ru.itis.kpfu.homework.screen;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.databinding.FragmentSettingBinding

class SettingFragment : Fragment(R.layout.fragment_setting) {
    private var binding: FragmentSettingBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingBinding.bind(view)

        binding?.run {

            button.setOnClickListener {
                findNavController().navigate(R.id.action_settingFragment_to_accountFragment)
            }
            button2.setOnClickListener {
                findNavController().navigate(R.id.action_settingFragment_to_privacyFragment)
            }
            button3.setOnClickListener {
                findNavController().navigate(R.id.action_settingFragment_to_appearanceFragment)
            }
            button4.setOnClickListener {
                findNavController().navigate(R.id.action_settingFragment_to_notificationFragment)
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}