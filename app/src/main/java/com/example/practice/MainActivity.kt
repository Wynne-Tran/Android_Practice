package com.example.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

        val button  = findViewById<Button>(R.id.btnActivity)
        button.setOnClickListener{callActivity()}

    }

    private fun callActivity() {
        var editText = findViewById<EditText>(R.id.etName)
        val message = editText.text.toString()
        val intent = Intent(this, MainActivity2::class.java).also {
            it.putExtra("EXTRA_MESSAGE", message)
            startActivity(it)
            editText.clearComposingText()
        }
    }

    // this fun prevent stack screen and create arrow back
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    // to prevent user click back to homescreen , go to navigative folder
    // on gragh arrow back from data - home, properties Pop Behavior
    // popUpTo : choice homeFragment
    // popUpToInclusive. : true

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.share_menu) {
            val share = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(Intent.EXTRA_TEXT, "https://www.linkedin.com/in/wynne-tran/")
                this.type = "text/plain"
            }
            startActivity(share)
        } else {
            return super.onOptionsItemSelected(item)
        }
        return true
    }
}