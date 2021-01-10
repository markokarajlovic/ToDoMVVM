package com.example.todomvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MainActivityAdapter(private val mainAdapterListener: MainAdapterListener) :
    RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {

    private var list: ArrayList<Todo> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ViewHolder(view, mainAdapterListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(newList: ArrayList<Todo>) {
        list.clear()
        list = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, private val mainAdapterListener: MainAdapterListener) :
        RecyclerView.ViewHolder(itemView) {

        private val checkBox = itemView.findViewById<CheckBox>(R.id.checkBoxItemTodo)
        private val buttonDelete = itemView.findViewById<Button>(R.id.buttonItemTodo)

        fun bind(todo: Todo) {
            checkBox.isChecked = todo.isDone
            checkBox.text = todo.text

            checkBox.setOnClickListener {
                mainAdapterListener.onTodoChecked(todo)
            }

            buttonDelete.setOnClickListener {
                mainAdapterListener.onTodoDeleted(todo)
            }
        }
    }
}