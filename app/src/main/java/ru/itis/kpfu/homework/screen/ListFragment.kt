package ru.itis.kpfu.homework.screen;

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.launch
import ru.itis.kpfu.homework.Constants.APP_PREFERENCES
import ru.itis.kpfu.homework.Constants.ITEM_BINDING
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.adapter.GridSpaceItemDecorator
import ru.itis.kpfu.homework.adapter.TodoListAdapter
import ru.itis.kpfu.homework.adapter.LinearSpaceItemDecorator
import ru.itis.kpfu.homework.databinding.FragmentListBinding
import ru.itis.kpfu.homework.data.TodoListRepository
import ru.itis.kpfu.homework.data.entity.TodoList

class ListFragment : Fragment(R.layout.fragment_list) {
    private var binding: FragmentListBinding? = null
    private var listAdapter: TodoListAdapter? = null
    private var repository: TodoListRepository? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private lateinit var preferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
        setHasOptionsMenu(true)
        preferences = requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        repository = TodoListRepository(requireContext())

        val bindingFlag = preferences.getBoolean(ITEM_BINDING, true)

        binding?.run{
            lifecycleScope.launch {
                if ((repository?.getOnlyList() ?: listOf()).isEmpty()){
                    tvEmpty.text = "Для начала работы добавьте первую цель!"
                } else {
                    tvEmpty.visibility = View.GONE
                }
            }

            swipeRefreshLayout = swipeView
            listAdapter = TodoListAdapter(bindingFlag, {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, EditListFragment.newInstance(it.id,false))
                    .addToBackStack("ListFragment")
                    .commit()
            },{
                lifecycleScope.launch {
                    repository?.deleteTodoList(it)
                }
            })

            rvTodoList.adapter = listAdapter

            if (bindingFlag) {
                rvTodoList.layoutManager = LinearLayoutManager(requireContext())
                rvTodoList.addItemDecoration(LinearSpaceItemDecorator(requireContext(),16f))
            }
            else {
                rvTodoList.layoutManager = GridLayoutManager(requireContext(), 2)
                rvTodoList.addItemDecoration(GridSpaceItemDecorator(requireContext(),16f))
            }

            lifecycleScope.launch {
                repository?.getAllTodoLists()?.observe(viewLifecycleOwner) { todolist ->
                    listAdapter?.submitList(todolist)
                }
            }

//            lifecycleScope.launch {
//                (repository?.getAllTodoLists().also {
//                    listAdapter?.submitList(it)
//                }
//            }

            swipeRefreshLayout?.setOnRefreshListener {
                myUpdateOperation()
            }

            fbAdd.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, EditListFragment.newInstance(0, true))
                    .addToBackStack("ListFragment")
                    .commit()
            }

        }
    }

    private fun myUpdateOperation() {
        swipeRefreshLayout?.isRefreshing = false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.action_theme -> {
                true
            }
            R.id.action_layout -> {
                val bindingFlag = preferences.getBoolean(ITEM_BINDING, true)
                if (bindingFlag) {
                    preferences.edit {
                        putBoolean(ITEM_BINDING, false)
                        commit()
                    }
                } else {
                    preferences.edit {
                        putBoolean(ITEM_BINDING, true)
                        commit()
                    }
                }
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, EditListFragment())
                    .replace(R.id.fragment_container, ListFragment())
                    .commit()
                true
            }
            R.id.action_delete_all -> {
                lifecycleScope.launch {
                    repository?.deleteAll()
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        repository = null
        binding = null
    }

}