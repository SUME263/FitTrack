package com.example.fittrack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fittrack.databinding.ItemLogSessionBinding
import com.example.fittrack.models.LogSession

class LogSessionAdapter(private val sessions: List<LogSession>) :
    RecyclerView.Adapter<LogSessionAdapter.SessionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val binding = ItemLogSessionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SessionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        val session = sessions[position]
        holder.bind(session)
    }

    override fun getItemCount(): Int {
        return sessions.size
    }

    class SessionViewHolder(private val binding: ItemLogSessionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(session: LogSession) {
            binding.textViewTitle.text = session.title
            binding.textViewDuration.text = "${session.duration} minutes"
        }
    }
}
