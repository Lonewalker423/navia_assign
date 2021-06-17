package com.example.navia_assign.feature

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.navia_assign.core.data.*
import com.example.navia_assign.core.service.Service
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.random.Random.Default.nextInt

class DietRepo @Inject constructor(
    @ApplicationContext val context: Context,
    private val service: Service
) {
    fun fetchDietPlan(): Flow<DataState<List<Day>>> = flow {
        emit(DataState.Loading)
        try {
            val dietPlan = service.dietPlan()
            val listOfDays = dataMapper(dietPlan)
//            val dummyDays = ArrayList<Day>()
//            val dummyMeal = ArrayList<Meal>()
//            dummyMeal.add(Meal("dinner", "23:10"))
//            dummyMeal.add(Meal("dinner", "23:49"))
//            dummyDays.add(Day(5, "thursday", dummyMeal))
//            notificationSchedular(dummyDays)
            notificationSchedular(listOfDays)
            emit(DataState.Success(listOfDays))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    fun dataMapper(diet: Diet): List<Day> {
        val listOfDays = ArrayList<Day>()
        diet.week_diet_data.sunday?.let {
            listOfDays.add(Day(1, "sunday", it))
        }
        diet.week_diet_data.monday?.let {
            listOfDays.add(Day(2, "monday", it))
        }
        diet.week_diet_data.tuesday?.let {
            listOfDays.add(Day(3, "tuesday", it))
        }
        diet.week_diet_data.wednesday?.let {
            listOfDays.add(Day(4, "wednesday", it))
        }
        diet.week_diet_data.thursday?.let {
            listOfDays.add(Day(5, "thursday", it))
        }
        diet.week_diet_data.friday?.let {
            listOfDays.add(Day(6, "friday", it))
        }
        diet.week_diet_data.saturday?.let {
            listOfDays.add(Day(7, "saturday", it))
        }

        return listOfDays
    }

    private fun notificationSchedular(days: List<Day>) {
        days.forEach { day ->
            day.meals.forEach { meal ->
                setNotification(day.id, meal)
            }
        }
    }


    private fun setNotification(givenDay: Int, meal: Meal) {
        val timeInMilli = findDate(givenDay, meal.meal_time)
        if (Calendar.getInstance().timeInMillis > timeInMilli) {
            return
        }
        val intent = Intent(context, NotificationBroadCaster::class.java)
        intent.putExtra("name", meal.food)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            nextInt(1000),
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        alarmManager.setExact(AlarmManager.RTC, timeInMilli, pendingIntent)
    }


    private fun findDate(givenDay: Int, hours: String): Long {
        val cal = Calendar.getInstance()
        while (givenDay != cal.get(Calendar.DAY_OF_WEEK)) {
            cal.add(Calendar.DATE, 1)

        }
        val hourAndMinute = hours.split(":")
        cal.set(Calendar.HOUR_OF_DAY, hourAndMinute[0].toInt())
        cal.set(Calendar.MINUTE, hourAndMinute[1].toInt())
        cal.set(Calendar.SECOND, 0)
        return cal.timeInMillis
    }

}