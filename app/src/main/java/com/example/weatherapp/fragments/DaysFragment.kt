package com.example.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.adapters.RcAdapter
import com.example.weatherapp.databinding.FragmentDaysBinding
import com.example.weatherapp.viewmodels.DaysFragmentViewModel
import com.example.weatherapp.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DaysFragment : Fragment() {
    private lateinit var binding: FragmentDaysBinding
    private val adapter = RcAdapter()
    private val vm: DaysFragmentViewModel by viewModels()
    private val mainVm: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater)
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
        vm.daysWeatherList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        mainVm.cityName.observe(viewLifecycleOwner) {
            vm.setDaysWeatherList(it)
        }
    }
}