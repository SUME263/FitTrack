package com.fittrack.ui.exercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fittrack.R
import com.example.fittrack.ui.exercise.CategoryCard

class ExerciseCategoryAdapter(private val categories: List<CategoryCard>) :
    RecyclerView.Adapter<ExerciseCategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryText: TextView = itemView.findViewById(R.id.exerciseCategoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise_type, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryText.text = category.name

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "${category.name} clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = categories.size
}
