package com.example.todomvvm

interface MainAdapterListener {

    fun onTodoDeleted(todo: Todo)

    fun onTodoChecked(todo: Todo)
}