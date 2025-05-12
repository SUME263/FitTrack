package com.example.fittrack.ui.exercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fittrack.R

class ExerciseCardAdapter(private val exercises: List<ExerciseCard>, private val onStartClick: (ExerciseCard) -> Unit) :
    RecyclerView.Adapter<ExerciseCardAdapter.ExerciseViewHolder>() {

    // ViewHolder for each card
    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTextView: TextView = itemView.findViewById(R.id.textCategory)
        val nameTextView: TextView = itemView.findViewById(R.id.textExerciseName)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textExerciseDescription)
        val startButton: Button = itemView.findViewById(R.id.buttonStartExercise)

        init {
            startButton.setOnClickListener {
                val exercise = exercises[adapterPosition]
                onStartClick(exercise)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise_card, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.categoryTextView.text = exercise.category
        holder.nameTextView.text = exercise.name
        holder.descriptionTextView.text = exercise.description
    }

    override fun getItemCount(): Int {
        return exercises.size
    }
}
