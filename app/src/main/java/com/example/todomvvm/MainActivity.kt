package com.example.todomvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel
    private val adapter = MainActivityAdapter()

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
    }

    private fun initRecyclerView() {
        recyclerViewMain.setHasFixedSize(true)
        recyclerViewMain.layoutManager = LinearLayoutManager(this)
        recyclerViewMain.adapter = adapter
    }
}