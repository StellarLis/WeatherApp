package com.example.weatherapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.weatherapp.adapters.ViewPagerAdapter
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.viewmodels.MainActivityViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val vm: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //
        init()
    }
    private fun init() {
        checkPermissionAndSetCity()
        //
        val pagerAdapter = ViewPagerAdapter(this)
        binding.vPager.adapter = pagerAdapter
        binding.tabLayout.tabIconTint = null
        TabLayoutMediator(binding.tabLayout, binding.vPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Hours"
                else -> tab.text = "Days"
            }
        }.attach()
        //
        vm.cityName.observe(this) {
            vm.setWmModel(it)
        }
        vm.wModel.observe(this) {
            binding.tvDate.text = it.daysOrHours
            binding.tvLocation.text = vm.cityName.value
            binding.tvTempC.text = it.temp_c + "°С"
            binding.tvCondition.text = it.condition
        }
    }
    private fun checkPermissionAndSetCity() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                val location = task.result
                if (location != null) {
                    try {
                        val geocoder = Geocoder(this, Locale.getDefault())
                        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                        vm.cityName.value = addresses[0].locality
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 1)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            checkPermissionAndSetCity()
        } else {
            val dialog = AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("You need location permission to use this app!")
                .setPositiveButton("Ok") { dialog, int ->
                    dialog.cancel()
                    finish()
                }.create().show()
        }
    }
}