package com.example.saynotohunger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class FoodRequests: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_requests)

        findViewById<Button>(R.id.homePage).setOnClickListener {
            startActivity(Intent(this, HomePage::class.java))
        }

        findViewById<Button>(R.id.newFoodRequest).setOnClickListener {
            startActivity(Intent(this, NewFoodRequest::class.java))
        }

        findViewById<Button>(R.id.availableFood).setOnClickListener {
            startActivity(Intent(this, AvailableFood::class.java))
        }


    }
}
