package com.example.navia_assign.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navia_assign.core.data.Day
import com.example.navia_assign.databinding.WeekItemBinding
import java.time.DayOfWeek

class WeekAdapter(val days: List<Day>) : RecyclerView.Adapter<WeekAdapter.ViewHolder>() {
    class ViewHolder(val binding: WeekItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(day: Day) {
            binding.weekName.text = day.name
            binding.meals.apply {
                layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
                adapter = MealAdapter(day.meals)
            }
        }


        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val binding = WeekItemBinding.inflate(LayoutInflater.from(parent.context))
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(days[position])
    }

    override fun getItemCount() = days.size
}