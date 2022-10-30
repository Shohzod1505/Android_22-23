package ru.itis.kpfu.homework;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import ru.itis.kpfu.homework.adapter.FilmAdapter
import ru.itis.kpfu.homework.adapter.SpaceItemDecorator
import ru.itis.kpfu.homework.model.FilmRepository
import ru.itis.kpfu.homework.databinding.FragmentFirstBinding
import ru.itis.kpfu.homework.model.MainItem

class FirstFragment : Fragment(R.layout.fragment_first) {
    private var binding: FragmentFirstBinding? = null
    private var listAdapter: FilmAdapter? = null
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)
        binding?.run {
            swipeRefreshLayout = swipeView
            val itemDecoration = SpaceItemDecorator(requireContext(),16f)

            listAdapter = FilmAdapter(glide = Glide.with(this@FirstFragment)) {

            }

            rvFilm.adapter = listAdapter
            rvFilm.addItemDecoration(itemDecoration)
            rvFilm.adapter = ScaleInAnimationAdapter(listAdapter!!).apply {
                setDuration(500)
            }
            swipeRefreshLayout.setOnRefreshListener {
                rvFilm.adapter = ScaleInAnimationAdapter(listAdapter!!).apply {
                    setDuration(500)
                }
                myUpdateOperation()
            }

            listAdapter?.submitList(FilmRepository.mainItems)
        }
    }

    private fun myUpdateOperation() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}