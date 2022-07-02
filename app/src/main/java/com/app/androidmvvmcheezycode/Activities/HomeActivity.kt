package com.app.androidmvvmcheezycode.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.app.androidmvvmcheezycode.R
import com.app.androidmvvmcheezycode.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    var navController:NavController?=null
    var bottomNavigationView:BottomNavigationView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        navController = Navigation.findNavController(this,R.id.fragmentContainerView3)
        bottomNavigationView = findViewById(R.id.bottomnav)
        NavigationUI.setupWithNavController(bottomNavigationView!!,navController!!)
    }
}