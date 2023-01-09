package ru.itis.kpfu.homework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.data.entity.TodoList
import ru.itis.kpfu.homework.databinding.ItemTodoListLinearBinding

class LinearTodoListHolder(
    private val binding: ItemTodoListLinearBinding,
    private val action: (TodoList) -> Unit,
    private val delete: (TodoList) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private var todoList: TodoList? = null

    init {
        binding.ivDelete.setOnClickListener {
            todoList?.also(delete)
        }

        binding.root.setOnClickListener {
            todoList?.also(action)
        }
    }

    fun onBind(todoList: TodoList) {
        this.todoList = todoList
        with(binding) {
            tvTitle.text = todoList.title
            tvText.text = todoList.text
            ivDelete.setImageResource(R.drawable.ic_baseline_delete_24)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            action: (TodoList) -> Unit,
            delete: (TodoList) -> Unit
        ): LinearTodoListHolder = LinearTodoListHolder(
            binding = ItemTodoListLinearBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            action = action,
            delete = delete
        )
    }

}