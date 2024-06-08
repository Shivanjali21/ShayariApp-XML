package com.practice.shayariapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.practice.shayariapp.R
import com.practice.shayariapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
       ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.mainActRoot)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentCV) as NavHostFragment
        navController = navHost.navController

    }
}