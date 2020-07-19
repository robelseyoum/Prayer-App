package com.robelseyoum3.perseusprayer.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*
import javax.inject.Inject

class MainRepository @Inject constructor (val context: Context, var mFusedLocationClient: FusedLocationProviderClient) {


}