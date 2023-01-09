package ru.itis.kpfu.homework.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import ru.itis.kpfu.homework.data.entity.TodoList

@Dao
interface TodoListDao {

    @Insert(onConflict = REPLACE)
    suspend fun save(todoList: TodoList)

    @Update(onConflict = REPLACE)
    suspend fun edit(todoList: TodoList)

    @Delete
    suspend fun delete(todoList: TodoList)

//    @Query("SELECT * FROM lists ORDER BY id ASC")
//    fun getAll(): LiveData<List<TodoList>>

    @Query("SELECT * FROM lists ORDER BY id ASC")
    suspend fun getOnlyList(): List<TodoList>

    @Query("SELECT * FROM lists WHERE id = :id")
    suspend fun findById(id: Int): TodoList?

    @Query("DELETE FROM lists")
    suspend fun deleteAll()

}