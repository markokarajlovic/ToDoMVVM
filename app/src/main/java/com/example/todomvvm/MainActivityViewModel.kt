package com.example.todomvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    val todoList: MutableLiveData<ArrayList<Todo>> = MutableLiveData()

    init {
        todoList.value = arrayListOf()
    }

    fun addNewTodo(todo: Todo) {
        val helperList = arrayListOf<Todo>()
        helperList.addAll(todoList.value!!)
        helperList.add(todo)
        todoList.value = helperList
    }

    fun deleteTodo(todo: Todo) {
        val helperList = arrayListOf<Todo>()
        helperList.addAll(todoList.value!!)
        helperList.remove(todo)
        todoList.value = helperList
    }

    fun todoChecked(todo: Todo) {
        todoList.value?.map {
            if (it.id == todo.id) {
                it.isDone = !it.isDone
            }
        }
    }
}