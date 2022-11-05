package com.example.light

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var s_mgr: SensorManager;
    private lateinit var s: Sensor;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        s_mgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        s = s_mgr.getDefaultSensor(Sensor.TYPE_LIGHT)
        s_mgr.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        val textView = findViewById<TextView>(R.id.dataView)
        textView.text = p0!!.values[0].toString()
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onDestroy() {
        super.onDestroy()
        s_mgr.unregisterListener(this)
    }
}