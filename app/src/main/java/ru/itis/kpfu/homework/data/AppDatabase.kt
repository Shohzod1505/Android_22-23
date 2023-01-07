package ru.itis.kpfu.homework.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.itis.kpfu.homework.data.dao.TodoListDao
import ru.itis.kpfu.homework.data.entity.TodoList

@Database(entities = [TodoList::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getTodoListDao(): TodoListDao

}