package com.example.fittrack.ui.goals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fittrack.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GoalsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_goals, container, false)

        val fab = view.findViewById<FloatingActionButton>(R.id.fabAddGoal)
        fab.setOnClickListener {
            Toast.makeText(requireContext(), "New goal added", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}