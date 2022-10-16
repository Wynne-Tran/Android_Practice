package com.example.practice

import android.content.Context
import android.content.Intent
import android.content.UriPermission
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity2 : AppCompatActivity() {

    val FINE_LOCATION_RO = 101
    val CAMERA_RO = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val message = intent.getStringExtra("EXTRA_MESSAGE")
        val textView = findViewById<TextView>(R.id.result).apply {
            text = message
        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{goBackMainActivity()}

        val btn_Location = findViewById<Button>(R.id.local)
        btn_Location.setOnClickListener{
            checkForPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, "location", FINE_LOCATION_RO)
        }

        val btn_Camera = findViewById<Button>(R.id.camera)
        btn_Camera.setOnClickListener{
            checkForPermission(android.Manifest.permission.CAMERA, "camera", CAMERA_RO)
        }
    }

    private fun goBackMainActivity() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }


    private fun checkForPermission(permission: String, name: String, requestCode: Int){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when {
                ContextCompat.checkSelfPermission(applicationContext, permission) == PackageManager.PERMISSION_GRANTED -> {
                    Toast.makeText(applicationContext, "$name permission granted", Toast.LENGTH_LONG).show()
                }
                shouldShowRequestPermissionRationale(permission) -> showDialog(permission, name, requestCode)
                else -> ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        fun innerCheck(name: String) {
            if( grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "$name permission refused !", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "$name permission granted", Toast.LENGTH_LONG).show()
            }
        }

        when (requestCode) {
            FINE_LOCATION_RO -> innerCheck("location")
            CAMERA_RO -> innerCheck("camera")
        }
    }

    private fun showDialog(permission: String, name: String, requestCode: Int) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setMessage("Permission to access your $name is required to use this app")
            setTitle("Permission required")
            setPositiveButton("Ok") {
                dialog, which -> ActivityCompat.requestPermissions(this@MainActivity2, arrayOf(permission), requestCode)
            }
            val dialog = builder.create()
            dialog.show()
        }
    }
}