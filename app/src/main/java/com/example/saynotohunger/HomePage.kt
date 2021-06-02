package com.example.saynotohunger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomePage: AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        findViewById<Button>(R.id.goToVolunteerRegister).setOnClickListener {
            startActivity(Intent(this, VolunteerRegister::class.java))
        }
    }
}