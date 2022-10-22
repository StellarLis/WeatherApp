package com.example.weatherapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.WeatherModel
import com.example.domain.usecases.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    val wModel = MutableLiveData<WeatherModel>()
    var cityName = MutableLiveData<String>()

    fun setWmModel(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val wm = getCurrentWeatherUseCase.execute(city)
            wModel.postValue(wm)
        }
    }
}