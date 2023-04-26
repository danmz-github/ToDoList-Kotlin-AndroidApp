package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val taskList = arrayListOf<String>()

        val arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_multiple_choice,
            taskList)

        val addButton: Button = findViewById(R.id.addButton)
        val deleteButton : Button = findViewById(R.id.deleteButton)
        val newTaskEntry: EditText = findViewById(R.id.newTaskEntry)
        val toDoListView : ListView = findViewById(R.id.toDoListView)

        addButton.setOnClickListener {
            taskList.add(newTaskEntry.text.toString())

            toDoListView.adapter = arrayAdapter

            arrayAdapter.notifyDataSetChanged()

            newTaskEntry.text.clear()
        }

        deleteButton.setOnClickListener {

            val positionChecked: SparseBooleanArray = toDoListView.checkedItemPositions

            var deleteItem = toDoListView.count - 1

            while (deleteItem >= 0) {
                if (positionChecked.get(deleteItem)) {
                    arrayAdapter.remove(taskList[deleteItem])
                }
                deleteItem--
            }

            positionChecked.clear()
            arrayAdapter.notifyDataSetChanged()
        }
    }
}