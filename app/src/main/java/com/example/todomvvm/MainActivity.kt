package com.example.todomvvm

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainAdapterListener {

    lateinit var viewModel: MainActivityViewModel
    private val adapter = MainActivityAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainActivityViewModel::class.java
        )

        initRecyclerView()

        viewModel.todoList.observe(this, {
            adapter.setList(it)
        })

        fabMain.setOnClickListener {
            startNewTodo()
        }
    }

    private fun startNewTodo() {

        val input = EditText(this)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add New Todo")
            .setCancelable(false)
            .setView(input)
            .setPositiveButton("Add") { dialogInterface, _ ->
                viewModel.addNewTodo(Todo(isDone = false, text = input.text.toString()))
                dialogInterface.cancel()
            }
            .setNegativeButton("Cancel") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .show()
    }

    private fun initRecyclerView() {
        recyclerViewMain.setHasFixedSize(true)
        recyclerViewMain.layoutManager = LinearLayoutManager(this)
        recyclerViewMain.adapter = adapter
    }

    override fun onTodoDeleted(todo: Todo) {
        viewModel.deleteTodo(todo)
    }

    override fun onTodoChecked(todo: Todo) {
        viewModel.todoChecked(todo)
    }
}