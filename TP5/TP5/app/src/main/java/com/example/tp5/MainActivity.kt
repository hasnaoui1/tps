package com.example.tp5

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.tp5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),SensorEventListener, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val sensorMgr = getSystemService(SENSOR_SERVICE) as SensorManager
        var monAccelero = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        var gyroscope = sensorMgr.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        var magneto = sensorMgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        var prox = sensorMgr.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        var photo = sensorMgr.getDefaultSensor(Sensor.TYPE_LIGHT);
        when (item.itemId) {
            R.id.nav_Accelerometer -> {

                    sensorMgr.registerListener(this, monAccelero, SensorManager.SENSOR_DELAY_NORMAL)

            }
            R.id.nav_Gyroscope -> {

                    sensorMgr.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL)

            }
            R.id.nav_Magnetometer -> {

                    sensorMgr.registerListener(this, magneto, SensorManager.SENSOR_DELAY_NORMAL)

            }
            R.id.nav_Proximity -> {

                    sensorMgr.registerListener(this, prox, SensorManager.SENSOR_DELAY_NORMAL)

            }
            R.id.nav_Photometer-> {

                    sensorMgr.registerListener(this, photo, SensorManager.SENSOR_DELAY_NORMAL)

            }

        }


        return true;

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            when (event.sensor.type) {
                Sensor.TYPE_ACCELEROMETER -> {
                    val x = event.values[0]
                    val y = event.values[1]
                    val z = event.values[2]
                    println("Accelerometer values: x=$x, y=$y, z=$z")
                }
                Sensor.TYPE_GYROSCOPE -> {
                    val x = event.values[0]
                    val y = event.values[1]
                    val z = event.values[2]
                    println("Gyroscope values: x=$x, y=$y, z=$z")
                }
                Sensor.TYPE_MAGNETIC_FIELD -> {
                    val x = event.values[0]
                    val y = event.values[1]
                    val z = event.values[2]
                    println("Magnetometer values: x=$x, y=$y, z=$z")
                }
                Sensor.TYPE_PROXIMITY -> {
                    val distance = event.values[0]
                    println("Proximity value: distance=$distance cm")
                }
                Sensor.TYPE_LIGHT -> {
                    val lightLevel = event.values[0]
                    println("Light sensor value: lightLevel=$lightLevel lx")
                }
            }
        }
    }


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        println("Not yet implemented")
    }
}

