package com.example.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.WeatherModel
import com.example.domain.usecases.GetHoursWeatherListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoursFragmentViewModel @Inject constructor(
    private val getHoursWeatherListUseCase: GetHoursWeatherListUseCase
) : ViewModel() {

    val hoursWeatherList = MutableLiveData<List<WeatherModel>>()

    fun setHoursWeatherList(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = getHoursWeatherListUseCase.execute(city)
            hoursWeatherList.postValue(list)
        }
    }
}