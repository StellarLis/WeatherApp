package com.example.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.WeatherModel
import com.example.domain.usecases.GetDaysWeatherListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DaysFragmentViewModel @Inject constructor(
    private val getDaysWeatherListUseCase: GetDaysWeatherListUseCase
) : ViewModel() {

    val daysWeatherList = MutableLiveData<List<WeatherModel>>()

    fun setDaysWeatherList(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = getDaysWeatherListUseCase.execute(city)
            daysWeatherList.postValue(list)
        }
    }
}