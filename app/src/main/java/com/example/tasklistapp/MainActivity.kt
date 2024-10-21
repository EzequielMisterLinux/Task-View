package com.example.tasklistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


data class Task(
    val title: String,
    val description: String,
    val priority: String,
    val deadline: String
)

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val tasks = listOf(
            Task("Tarea 1", "Descripción de la tarea 1", "Alta", "24/10/16"),
            Task("Tarea 2", "Descripción de la tarea 2", "Media", "25/10/16"),
            Task("Tarea 3", "Descripción de la tarea 3", "Baja", "26/10/16")
        )

        adapter = TaskAdapter(tasks)
        recyclerView.adapter = adapter
    }
}

class TaskAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.task_title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.task_description)
        val priorityTextView: TextView = itemView.findViewById(R.id.task_priority)
        val deadlineTextView: TextView = itemView.findViewById(R.id.task_deadline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.titleTextView.text = task.title
        holder.descriptionTextView.text = task.description
        holder.priorityTextView.text = task.priority
        holder.deadlineTextView.text = "Due Date: ${task.deadline}"
    }

    override fun getItemCount() = tasks.size
}