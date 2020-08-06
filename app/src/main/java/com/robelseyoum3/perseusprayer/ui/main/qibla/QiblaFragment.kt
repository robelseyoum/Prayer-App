package com.robelseyoum3.perseusprayer.ui.main.qibla

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Toast
import com.robelseyoum3.perseusprayer.R
import kotlinx.android.synthetic.main.qibla_fragment.*


class QiblaFragment : BaseQiblaFragment(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    lateinit var sensor: Sensor
    var currentDegree: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = activity!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION)

        if (sensor != null) {
            sensorManager.registerListener(
                this,
                sensor,
                SensorManager.SENSOR_DELAY_FASTEST
            )
        } else {
            Toast.makeText(context?.applicationContext, "not support", Toast.LENGTH_SHORT).show()
        }


    }
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
        initSensor()
    }

    private fun initSensor() {
    }


    override fun onSensorChanged(event: SensorEvent?) {

        val degree = Math.round(event!!.values[0])
        val animation = RotateAnimation(
            currentDegree,
            (-degree).toFloat(),
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
    )

        animation.duration = 500
        animation.fillAfter = true
        ic_compass.animation = animation

        currentDegree = -degree.toFloat()
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}