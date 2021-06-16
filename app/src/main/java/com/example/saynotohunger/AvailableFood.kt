package com.example.saynotohunger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AvailableFood: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.available_food)

        findViewById<Button>(R.id.availableHome).setOnClickListener {
            startActivity(Intent(this, HomePage::class.java))
        }
    }
}