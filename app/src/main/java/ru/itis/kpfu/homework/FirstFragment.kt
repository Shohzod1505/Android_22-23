package ru.itis.kpfu.homework;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import ru.itis.kpfu.homework.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first) {
    private var binding: FragmentFirstBinding? = null
    private var adapter: FilmAdapter? = null
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)

        binding?.run {
            swipeRefreshLayout = swipeView
            val itemDecoration = SpaceItemDecorator(requireContext(),16f)
            adapter = FilmAdapter(
                list = FilmRepository.films,
                glide = Glide.with(this@FirstFragment)
            ) {
                parentFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out,
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    )
                    .replace(R.id.fragment_container, SecondFragment.newInstance(it.id))
                    .addToBackStack("FirstFragment")
                    .commit()
            }
            rvFilm.adapter = adapter
            rvFilm.addItemDecoration(itemDecoration)
            rvFilm.adapter = ScaleInAnimationAdapter(adapter!!).apply {
                setDuration(500)
                setFirstOnly(false)
            }

            swipeRefreshLayout.setOnRefreshListener {
                rvFilm.adapter = ScaleInAnimationAdapter(adapter!!).apply {
                    setDuration(500)
                    setFirstOnly(false)
                }
                myUpdateOperation()
            }
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