package com.example.fittrack.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fittrack.R
import com.example.fittrack.ui.home.ExerciseType

// ExerciseType data class
data class ExerciseType(val name: String)

class ExerciseTypeAdapter(private val exerciseTypes: List<ExerciseType>) :
    RecyclerView.Adapter<ExerciseTypeAdapter.ExerciseTypeViewHolder>() {
        
    inner class ExerciseTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val typeName: TextView = itemView.findViewById(R.id.exerciseCategoryName) // assuming you have this ID
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseTypeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise_type, parent, false)
        return ExerciseTypeViewHolder(itemView)
    }
    
    override fun onBindViewHolder(holder: ExerciseTypeViewHolder, position: Int) {
        val currentExerciseType = exerciseTypes[position]
        holder.typeName.text = currentExerciseType.name
    }

    // Return the size of the dataset (invoked by the layout manager)
    override fun getItemCount() = exerciseTypes.size
}
