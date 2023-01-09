package ru.itis.kpfu.homework

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import ru.itis.kpfu.homework.adapter.FilmAdapter
import ru.itis.kpfu.homework.adapter.SpaceItemDecorator
import ru.itis.kpfu.homework.adapter.SwipeToDelete
import ru.itis.kpfu.homework.databinding.DialogAddBinding
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
                deleteItem(it)
            }

            rvFilm.adapter = listAdapter
            rvFilm.addItemDecoration(itemDecoration)
            rvFilm.adapter = ScaleInAnimationAdapter(listAdapter!!).apply {
                setDuration(500)
            }
            swipeToDelete(rvFilm)
            swipeRefreshLayout.setOnRefreshListener {
                rvFilm.adapter = ScaleInAnimationAdapter(listAdapter!!).apply {
                    setDuration(500)
                }
                myUpdateOperation()
            }

            listAdapter?.submitList(FilmRepository.mainItems)

            fbDialog.setOnClickListener {
                addItem()
            }

        }
    }

    private fun deleteItem(film: MainItem.FilmUiModel){
        val currentList =  listAdapter?.currentList?.toMutableList()
        currentList?.remove(film)
        listAdapter?.submitList(currentList)
    }

    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDelete = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val itemToDelete = listAdapter?.currentList?.get(viewHolder.adapterPosition)
                deleteItem(itemToDelete as MainItem.FilmUiModel)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun addItem() {
        val dialogBinding = DialogAddBinding.inflate(layoutInflater)
        val currentList =  listAdapter?.currentList?.toMutableList()
        val dialogAdd = AlertDialog.Builder(requireContext())
            .setTitle("Add film")
            .setView(dialogBinding.root)
            .setPositiveButton("Ok", null)
            .setNegativeButton("Cancel", null)
            .create()

        dialogAdd.setOnShowListener {
            dialogBinding.etAddTitle.requestFocus()
            dialogAdd.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val title = dialogBinding.etAddTitle.text.toString()
                val description = dialogBinding.etAddDescription.text.toString()
                val position = dialogBinding.etAddPosition.text.toString()
                val listSize = currentList?.size
                if(title.isNotBlank()) {
                    val film = MainItem.FilmUiModel( listSize!! + 1, title, 3000, description, 10.0, "https://th.bing.com/th/id/OIP.qbVffTnS4-9RZzGnDawt2wHaLH?pid=ImgDet&rs=1")
                    if(position.isBlank()) {
                        currentList.add( listSize, film)
                    } else if(position.toInt() > listSize){
                        currentList.add( listSize, film)
                    } else {
                        currentList.add(position.toInt(), film)
                    }
                    listAdapter?.submitList(currentList)
                    dialogAdd.dismiss()
                } else {
                    dialogBinding.etAddTitle.error = "Please enter the title"
                    return@setOnClickListener
                }
            }
        }
        dialogAdd.show()
    }


    private fun myUpdateOperation() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}