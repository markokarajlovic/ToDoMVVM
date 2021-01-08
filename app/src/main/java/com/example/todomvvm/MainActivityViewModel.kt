package com.example.todomvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class MainActivityViewModel : ViewModel() {

    val todoList: MutableLiveData<ArrayList<Todo>> = MutableLiveData()

    init {
        FirebaseFirestore.getInstance()
            .collection("Todos")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val list: ArrayList<Todo> = arrayListOf()
                    if (task.result != null)
                        for (document in task.result!!.documents) {
                            val todo: Todo? = document.toObject(Todo::class.java)
                            list.add(todo!!)
                        }
                    todoList.value = list
                }
            }
    }
}