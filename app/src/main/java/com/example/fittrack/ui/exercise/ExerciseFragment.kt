package com.example.fittrack.ui.exercise

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fittrack.LogSessionActivity
import com.example.fittrack.R
import com.fittrack.ui.exercise.ExerciseCategoryAdapter

class ExerciseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Setup for category RecyclerView
            val categoryRecycler = view.findViewById<RecyclerView>(R.id.exerciseCategoryRecycler)

            val categories = listOf(
                CategoryCard("Running"),
                CategoryCard("Swimming"),
                CategoryCard("Lifting"),
                CategoryCard("Cycling")
            )

            categoryRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            categoryRecycler.adapter = ExerciseCategoryAdapter(categories)

        val logSessionButton: Button = view.findViewById(R.id.buttonLogSession)
        logSessionButton.setOnClickListener {
            val intent = Intent(requireContext(), LogSessionActivity::class.java)
            startActivity(intent)
        }

            // Setup for exercise card RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerExerciseCards)

            val exercises = listOf(
                ExerciseCard("Run", "Morning Run", "5K morning run"),
                ExerciseCard("Yoga", "Sun Salutation", "15 min flow"),
                ExerciseCard("Cycling", "Evening Ride", "10K ride"),
                ExerciseCard("Strength", "Upper Body", "Push & Pull workout"),
                ExerciseCard("HIIT", "Fat Burner", "20 min session"),
                ExerciseCard("Walk", "Lunchtime Walk", "2K brisk walk")
            )


        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ExerciseCardAdapter(exercises) { exercise ->
            // Handle the start button click here
        }
    }
}
