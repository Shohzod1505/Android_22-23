package ru.itis.kpfu.homework.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.data.entity.TodoList
import java.lang.IllegalStateException

class TodoListAdapter(
    private val itemBinding: Boolean,
    private val action: (TodoList) -> Unit,
    private val delete: (TodoList) -> Unit,
) : ListAdapter<TodoList, RecyclerView.ViewHolder>(TodoListDiff) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = if(itemBinding){
            LinearTodoListHolder.create(parent, action, delete)
        }
        else {
            GridTodoListHolder.create(parent, action, delete)
        }


    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is LinearTodoListHolder -> {
                holder.onBind(getItem(position))
            }
            is GridTodoListHolder -> {
                holder.onBind(getItem(position))
            }
        }

    }

}