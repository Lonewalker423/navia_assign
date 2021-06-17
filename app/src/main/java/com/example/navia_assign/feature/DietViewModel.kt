package com.example.navia_assign.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navia_assign.core.data.DataState
import com.example.navia_assign.core.data.Day
import com.example.navia_assign.core.data.Diet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DietViewModel @Inject constructor(private val dietRepo: DietRepo) : ViewModel() {
    private val _dietPlan = MutableLiveData<DataState<List<Day>>>()
    val dietPlain: LiveData<DataState<List<Day>>> get() = _dietPlan

    init {
        fetchDietPlain()
    }

    private fun fetchDietPlain() {
        viewModelScope.launch {
            dietRepo.fetchDietPlan().collect {
                _dietPlan.value = it
            }
        }
    }
}