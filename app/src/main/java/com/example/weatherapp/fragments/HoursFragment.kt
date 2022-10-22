package com.example.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.R
import com.example.weatherapp.adapters.RcAdapter
import com.example.weatherapp.databinding.FragmentHoursBinding
import com.example.weatherapp.viewmodels.HoursFragmentViewModel
import com.example.weatherapp.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoursFragment : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    private val adapter = RcAdapter()
    private val vm: HoursFragmentViewModel by viewModels()
    private val mainVm: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHoursBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        init()
    }
    private fun init() {
        binding.rcView.adapter = adapter
        //
        vm.hoursWeatherList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        mainVm.cityName.observe(viewLifecycleOwner) {
            vm.setHoursWeatherList(it)
        }
    }
}