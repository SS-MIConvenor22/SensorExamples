package com.example.accelerometer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), SensorEventListener{
    private lateinit var s_mgr: SensorManager;
    private lateinit var s: Sensor;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        s_mgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        s = s_mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        s_mgr.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(p0: SensorEvent?) {

      /*  Guide to the format of sensor data is here:
        https://developer.android.com/guide/topics/sensors/sensors_motion.html#sensors-motion-grav
        For accelerometer, values[0] is X, values[1] is Y and 2 is Z*/

        val x = p0!!.values[0];
        val y = p0.values[1];
        val z = p0.values[2];
        val textView = findViewById<TextView>(R.id.dataView);
        textView.text = "ACCELEROMETER\n" + "X:" + x + "\n" + "Y:" + y + "\n" + "Z:" + z;

    }


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onDestroy() {
        super.onDestroy()
        s_mgr.unregisterListener(this)
    }
}