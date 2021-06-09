package com.example.saynotohunger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class FoodRequests: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_requests)

        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            findViewById<Button>(R.id.newFoodRequest).setOnClickListener {
                startActivity(Intent(this, NewFoodRequest::class.java))
            }
        }

        else {
                Toast.makeText(baseContext, "Login to access", Toast.LENGTH_SHORT).show()
            }
        }
    }
