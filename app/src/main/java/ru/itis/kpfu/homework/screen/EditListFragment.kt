package ru.itis.kpfu.homework.screen;

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.data.TodoListRepository
import ru.itis.kpfu.homework.data.entity.TodoList
import ru.itis.kpfu.homework.databinding.FragmentEditListBinding

class EditListFragment : Fragment(R.layout.fragment_edit_list) {
    private var binding: FragmentEditListBinding? = null
    private var repository: TodoListRepository? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditListBinding.bind(view)
        repository = TodoListRepository(requireContext())

        binding?.run {

            arguments?.getBoolean(ARG_ACTION)?.also {
                if (it) {
                    btSave.setOnClickListener {
                        val title = etTitle.text.toString()
                        val text = etText.text.toString()
                        lifecycleScope.launch {
                            repository?.saveTodoList(TodoList(0, title, text,null,null,null))
                        }
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, ListFragment())
                            .addToBackStack("EditListFragment")
                            .commit()
                    }
                }
                else {
                    val id = arguments?.getInt(ARG_ID)
                    if (id != null) {
                        lifecycleScope.launch {
                            repository?.findById(id).also { todoList ->
                                etTitle.setText(todoList?.title)
                                etText.setText(todoList?.text)
                            }
                        }
                        btSave.setOnClickListener {
                            val title = etTitle.text.toString()
                            val text = etText.text.toString()
                            lifecycleScope.launch {
                                repository?.editTodoList(TodoList(id, title, text, null, null, null))
                            }
                            parentFragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, ListFragment())
                                .addToBackStack("EditListFragment")
                                .commit()
                        }
                    }


                }
            }

        }

    }

    companion object {
        private const val ARG_ACTION = "ARG_ACTION"
        private const val ARG_ID = "ARG_ID"
        fun newInstance(id: Int, action: Boolean) = EditListFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ID, id)
                putBoolean(ARG_ACTION, action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        repository = null
        binding = null
    }

}