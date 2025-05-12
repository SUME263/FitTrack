package com.example.fittrack.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fittrack.databinding.FragmentHomeBinding
import com.example.fittrack.ui.ExerciseTypeAdapter
import com.example.fittrack.ui.StatisticsAdapter
import com.example.fittrack.ui.statistics.Statistics

// Exercise classes
data class Exercise(val name: String, val description: String) {
}

//for the carousel
data class ExerciseType(val name: String)

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //stats cards
    val stats = listOf(
        Statistics("Calories", "450 kcal"),
        Statistics("Steps", "8,200"),
        Statistics("Duration", "35 min"),
        Statistics("Workouts", "4 done")
    )

    //carousel
    private val exerciseTypes = listOf(
        ExerciseType("Cardio"),
        ExerciseType("Strength"),
        ExerciseType("Flexibility"),
        ExerciseType("HIIT")
    )

    // Sample exercises
    private val exerciseList = listOf(
        Exercise("Push-ups", "A basic upper body exercise."),
        Exercise("Squats", "A lower body exercise targeting the thighs."),
        Exercise("Plank", "A core exercise to build strength."),
        Exercise("Burpees", "A full-body workout."),
        Exercise("Push-ups", "A basic upper body exercise."),
        Exercise("Squats", "A lower body exercise targeting the thighs."),
        Exercise("Plank", "A core exercise to build strength."),
        Exercise("Burpees", "A full-body workout.")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // display the logged in username
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.text.observe(viewLifecycleOwner) {
            binding.userNameTxt.text = it
        }

        //stats row
        binding.statsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.statsRecyclerView.adapter = StatisticsAdapter(stats)

        // Exercise Types Carousel (Horizontal)
        val typeAdapter = ExerciseTypeAdapter(exerciseTypes)
        binding.exerciseTypeCarousel.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.exerciseTypeCarousel.adapter = typeAdapter

        // Main Exercises List (Vertical)
        binding.exercisesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = ExerciseAdapter(exerciseList)
        binding.exercisesRecyclerView.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
