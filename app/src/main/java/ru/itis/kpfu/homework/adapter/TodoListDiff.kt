package ru.itis.kpfu.homework.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.itis.kpfu.homework.data.entity.TodoList

object TodoListDiff : DiffUtil.ItemCallback<TodoList>(){
    override fun areItemsTheSame(
        oldItem: TodoList,
        newItem: TodoList
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: TodoList,
        newItem: TodoList
    ): Boolean = oldItem == newItem
}