package com.robelseyoum3.perseusprayer.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.robelseyoum3.perseusprayer.R
import com.robelseyoum3.perseusprayer.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navController =  findNavController(R.id.main_nav_host_fragment)

        //For app bar title for each fragment
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.prayerTimesFragment, R.id.managePlacesFragment, R.id.qiblaFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)

    }

    private fun setupActionBar(){
        setSupportActionBar(tool_bar)
    }

    override fun displayProgressBar(boolean: Boolean) {
        if(boolean){
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.INVISIBLE
        }
    }
}
