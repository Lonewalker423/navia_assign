package com.example.navia_assign.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.navia_assign.core.data.Meal
import com.example.navia_assign.databinding.MealItemBinding

class MealAdapter(val meals: List<Meal>) : RecyclerView.Adapter<MealAdapter.ViewHolder>() {
    class ViewHolder(val binding: MealItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meal: Meal) {
            binding.foodName.text = meal.food
            binding.time.text = "Time : ${meal.meal_time}"
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val binding = MealItemBinding.inflate(LayoutInflater.from(parent.context))
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    override fun getItemCount() = meals.size
}