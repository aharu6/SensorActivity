package com.example.sensoractivity
import android.app.Activity
import android.hardware.Sensor
import android.hardware.Sensor.TYPE_ACCELEROMETER
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : Activity() ,SensorEventListener{
    private lateinit var sensorManager: SensorManager
    private var AccSensor:Sensor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        AccSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        var x:Float
        var y:Float
        var z:Float
        if (event?.sensor?.type === Sensor.TYPE_ACCELEROMETER){
            x = event.values[0]
            y = event.values[1]
            z = event.values[2]

            val intx = """$x"""
            val viewx:TextView = findViewById(R.id.xvalue)
            viewx.setText(intx)

            val inty = """$y"""
            val viewy:TextView = findViewById(R.id.yvalue)
            viewy.setText(inty)

            val intz = """$z"""
            val viewz:TextView = findViewById(R.id.zvalue)
            viewz.setText(intz)


        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,AccSensor,SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}