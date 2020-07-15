package com.robelseyoum3.perseusprayer.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.robelseyoum3.perseusprayer.R
import com.robelseyoum3.perseusprayer.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity() {


    private val PERMISSION_ID = 42
    //Fused Location Provider API to get users current position.
    lateinit var mFusedLocationClient: FusedLocationProviderClient

    private lateinit var currentDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()
        mFusedLocationClient  = LocationServices.getFusedLocationProviderClient(this)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        val navController =  findNavController(R.id.main_nav_host_fragment)

        //For app bar title for each fragment
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.prayerTimesFragment, R.id.managePlacesFragment, R.id.qiblaFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)

        getLastLocation()
        currentDate()
    }

    private fun currentDate(){
        currentDate = SimpleDateFormat("yyy-MM-dd", Locale.getDefault()).format(Date())
        Log.d(TAG, "Dates : $currentDate")
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

//    override fun setLocationCoordination(latitude: String, longitude: String) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    override fun setLocationCoordination(latitude: String, longitude: String) {
        //set the coordination
        Log.d(TAG, "setLocationCoordination: Latitude: $latitude")
        Log.d(TAG, "setLocationCoordination: Longitude: $longitude")
    }

//    override fun getCoordination(){
//        //return the coordination
//    }

    /**
     * get location data is code snippet is inspired from
     * https://www.androdocs.com/tutorials/getting-current-location-latitude-longitude-in-android-using-kotlin.html
     */

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        val latitude = location.latitude.toString()
                        Log.d(TAG, "Latitude: $latitude")
                        val longitude = location.longitude.toString()
                        Log.d(TAG, "Longitude: $longitude")
//                        passCoordination(latitude, longitude)
                        setLocationCoordination(latitude, longitude)
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    /**
     * here we used to  pass the value
     */
//    private fun passCoordination(latitude: String, longitude: String)  {
//        currentDate = SimpleDateFormat("yyy-MM-dd", Locale.getDefault()).format(Date())
//        val coordinateNumber = IssPassFragment.newInstance(latitude, longitude, currentDate)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, coordinateNumber, "IssPassFragment")
//            .commit()
//    }


    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
        }
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Granted. Start getting the location information
            }
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

}
