package com.example.navia_assign.feature

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navia_assign.R
import com.example.navia_assign.core.base.BaseFragment
import com.example.navia_assign.core.data.DataState
import com.example.navia_assign.databinding.FragmentDietBinding
import com.example.navia_assign.feature.adapter.WeekAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class DietPlanFragment : BaseFragment<FragmentDietBinding>() {
    override fun layoutId() = R.layout.fragment_diet
    private val viewModel: DietViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createNotificationChannel(requireContext(),
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "Meal notification channel.")
        viewModel.dietPlain.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Loading -> binding.progressOverlay.visibility = View.VISIBLE
                is DataState.Success -> {
                    dataState.data?.let {
                        binding.days.apply {
                            layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                            adapter = WeekAdapter(days = it)
                        }
                    }

                    binding.progressOverlay.visibility = View.GONE
                }
                is DataState.Error -> {
                    Snackbar.make(
                        view,
                        dataState.exception?.message.toString(),
                        Snackbar.LENGTH_SHORT
                    )
                    binding.progressOverlay.visibility = View.GONE
                }
            }
        })
    }


    fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean, name: String, description: String) {
        // 1
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // 2
            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            // 3
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

}