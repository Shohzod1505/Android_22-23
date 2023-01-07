package ru.itis.kpfu.homework.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "lists")
data class TodoList(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val text: String,
    val date: String?,
    val longitude: String?,
    val latitude: String?,
)
