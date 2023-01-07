package ru.itis.kpfu.homework.data

import android.content.Context
import androidx.room.Room
import ru.itis.kpfu.homework.data.entity.TodoList

class TodoListRepository(context: Context) {

    private val db by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
//            .allowMainThreadQueries()
//            .addTypeConverter(DateConverter::class)
            .build()
    }

    private val todoListDao by lazy{
        db.getTodoListDao()
    }

    suspend fun saveTodoList(todoList: TodoList) {
        todoListDao.save(todoList)
    }

    suspend fun editTodoList(todoList: TodoList) {
        todoListDao.edit(todoList)
    }

    suspend fun deleteTodoList(todoList: TodoList) {
        todoListDao.delete(todoList)
    }

    suspend fun getAllTodoLists(): List<TodoList> = todoListDao.getAll()

    suspend fun findById(id: Int): TodoList = todoListDao.findById(id)

    suspend fun deleteAll(todoList: List<TodoList>) {
        todoListDao.deleteAll(todoList)
    }

    companion object {
        const val DATABASE_NAME = "todo.db"
    }

}