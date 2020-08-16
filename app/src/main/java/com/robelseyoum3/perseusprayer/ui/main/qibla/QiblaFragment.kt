package com.robelseyoum3.perseusprayer.ui.main.qibla

import android.content.Context
import android.content.DialogInterface
import android.hardware.*
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.robelseyoum3.perseusprayer.R
import kotlinx.android.synthetic.main.qibla_fragment.*
import java.lang.Double
import kotlin.math.roundToInt


class QiblaFragment : BaseQiblaFragment(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    lateinit var sensor: Sensor
    var currentDegree: Float = 0.0f

    lateinit var mLastLocation: Location


    private var rotation: Sensor? = null
    private var acceleration: Sensor? = null
    private var magnetism: Sensor? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.qibla_fragment, container, false)
    }

    /**
    Gyroscope sensors, also known as gyro sensors measures angular or rotational movement.
    When you tilt or rotate your phone while playing videos or games, the gyro sensor adjusts the phone orientation accurately according to the phone movement.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "QiblaFragment: ${mainViewModel.hashCode()}")
        initLastLocation()
    }

    private fun initLastLocation() {
        sensorManager = activity!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mainViewModel.mLastLocation.observe(this, Observer {
            mLastLocation = it
        })
        start()
    }

    private fun start() {
        rotation = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        acceleration = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        magnetism = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        if (rotation == null) {
            if (acceleration == null || magnetism == null) {
                noSensorsFound()
            } else {
                sensorManager.registerListener(this, acceleration, SensorManager.SENSOR_DELAY_UI)
                sensorManager.registerListener(this, magnetism, SensorManager.SENSOR_DELAY_UI)
            }
        } else {
            sensorManager.registerListener(this, rotation, SensorManager.SENSOR_DELAY_UI)
        }
    }

    private fun stop() {
        for (sensor in listOf(acceleration, magnetism, rotation)) {
            sensor?.let { sensorManager.unregisterListener(this, it) }
        }
    }

    private fun noSensorsFound() {
        val builder = context?.applicationContext?.let { AlertDialog.Builder(it) }
        builder?.setMessage("Your device does not support the compass")?.setCancelable(true)
            ?.setNegativeButton("close") { _: DialogInterface?, _: Int ->
                //TODO?
            }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let { handleSensorEvent(it) }
    }

    private  fun handleSensorEvent(sensorEvent: SensorEvent){
        var head = sensorEvent.values[0].roundToInt().toFloat()
        val destinationLoc = Location("service Provider")
        destinationLoc.latitude = 21.422487 //kaaba latitude setting
        destinationLoc.longitude = 39.826206 //kaaba longitude setting
        var bearTo: Float = mLastLocation.bearingTo(destinationLoc)

        val geoField = GeomagneticField(
            Double.valueOf(mLastLocation.latitude).toFloat(), Double
                .valueOf(mLastLocation.longitude).toFloat(),
            Double.valueOf(mLastLocation.altitude).toFloat(),
            System.currentTimeMillis()
        )
        head -= geoField.declination

        if (bearTo < 0) {
            bearTo += 360
        }

        var direction = bearTo - head

        if (direction < 0) {
            direction += 360
        }

        ic_compass.rotation = direction
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}