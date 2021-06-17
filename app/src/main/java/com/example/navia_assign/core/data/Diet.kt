package com.example.navia_assign.core.data

data class Diet(
    val diet_duration: Int,
    val week_diet_data: WeekDietData
)

data class WeekDietData(
    val monday: List<Meal>?,
    val tuesday: List<Meal>?,
    val wednesday: List<Meal>?,
    val thursday: List<Meal>?,
    val friday : List<Meal>?,
    val saturday : List<Meal>?,
    val sunday : List<Meal>?,
)


data class Day(val id : Int , val name : String, val meals : List<Meal>)




data class Meal(
    val food: String,
    val meal_time: String
)

