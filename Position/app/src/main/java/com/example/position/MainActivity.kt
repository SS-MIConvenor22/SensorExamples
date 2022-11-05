package com.example.position

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity(), LocationListener{

    private lateinit var l_mgr: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        l_mgr = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // do we have permission to get location data?
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            startSensor()
        }
        else{

            val textView = findViewById<TextView>(R.id.dataView)
            textView.text = "Asking for permission..."
            ActivityCompat.requestPermissions(this, arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION),1)
        }
    }



    fun startSensor(){
        try{
            l_mgr.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                0f,
                this
            )
        }
        catch (e: SecurityException){
            val textView = findViewById<TextView>(R.id.dataView)
            textView.text = e.localizedMessage
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onLocationChanged(p0: Location) {
        Log.d("HELLO","Location changed")
        val textView = findViewById<TextView>(R.id.dataView)
        textView.text = "POSITION:\n" +
                "LAT: " + p0.latitude + "\n" +
                "LNG: " + p0.longitude + "\n" +
                "ACC: " + p0.accuracy + "m\n"
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("HELLO","Reached permissions")
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.d("HELLO","Permission granted")
            startSensor()
        }
        else{
            findViewById<TextView>(R.id.dataView).text = "Sorry, location permissions required..."
        }
    }
}