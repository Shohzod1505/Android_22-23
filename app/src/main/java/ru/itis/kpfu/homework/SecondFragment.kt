package ru.itis.kpfu.homework;

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.itis.kpfu.homework.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {
    private var binding: FragmentSecondBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondBinding.bind(view)
        setHasOptionsMenu(true)

        binding?.run {

            arguments?.getInt(ARG_ID)?.also {
                with(FilmRepository.films[it]) {
                    tvInfoId.text = "ID: $id"
                    tvInfoTitle.text = "Name: $title"
                    tvInfoYear.text = "Year: $year"
                    tvInfoGenre.text = "Genre: $genre"
                    tvInfoRate.text = "Rate: $rate"
                    Glide
                        .with(requireContext())
                        .load(cover)
                        .into(ivToolbar)

                }
            }

        }

    }

    companion object {

        private const val ARG_ID = "ARG_ID"

        fun newInstance(id: Int) = SecondFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ID, id)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.action_search -> {
                true
            }
            R.id.action_edit -> {
                true
            }
            R.id.action_camera -> {
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}